package vn.HiepKa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.HiepKa.configs.AzureConnectSQL;
import vn.HiepKa.dao.IBookDao;
import vn.HiepKa.models.BookModel;

public class BookDaoImpl extends AzureConnectSQL implements IBookDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public BookModel findById(int bookId) {
		String sql = "SELECT B.*, A.author_name FROM BOOK B " + "JOIN AUTHOR A ON B.author_id = A.author_id "
				+ "WHERE B.book_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			while (rs.next()) {
				BookModel book = new BookModel();
				book.setBookid(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthorid(rs.getInt("author_id"));
				book.setAuthorname(rs.getString("author_name"));
				book.setContent(rs.getString("content"));
				book.setCreatedat(rs.getDate("created_at"));
				book.setImagesbook(rs.getString("images_book"));
				return book;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    public static void main(String[] args) {
        BookDaoImpl bookDao = new BookDaoImpl();
        int bookIdToFind = 1;
        BookModel book = bookDao.findById(bookIdToFind);
        if (book != null) {
            System.out.println("Thông tin cuốn sách:");
            System.out.println("Book ID: " + book.getBookid());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author ID: " + (book.getAuthorid() != 0 ? String.valueOf(book.getAuthorid()) : "null"));
            System.out.println("Author Name: " + (book.getAuthorname() != null ? book.getAuthorname() : "null"));
            System.out.println("Content: " + (book.getContent() != null ? book.getContent() : "null"));
            System.out.println("Created At: " + (book.getCreatedat() != null ? book.getCreatedat() : "null"));
            System.out.println("Images Book: " + (book.getImagesbook() != null ? "Có ảnh" : "null"));
        } else {
            System.out.println("Không tìm thấy cuốn sách với ID: " + bookIdToFind);
        }

    }

	@Override
	public void insert(BookModel book) throws SQLException {
		String sql = "INSERT INTO BOOK (title, author_id, content, created_at, images_book) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getAuthorid());
			ps.setString(3, book.getContent());

			// Lấy ngày hiện tại nếu `created_at` chưa được gán
			if (book.getCreatedat() == null) {
				book.setCreatedat(new java.sql.Date(new java.util.Date().getTime())); // Gán ngày hiện tại
			}

			ps.setDate(4, new java.sql.Date(book.getCreatedat().getTime())); // Gán ngày vào câu truy vấn
			ps.setString(5, book.getImagesbook());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//    public static void main(String[] args) {
//        // Tạo đối tượng BookDaoImpl
//        BookDaoImpl bookDao = new BookDaoImpl();
//
//        // Tạo đối tượng BookModel với các giá trị cụ thể
//        BookModel newBook = new BookModel();
//        newBook.setTitle("Cô gái đến từ hôm qua");
//        newBook.setAuthorid(1); // ID của tác giả, bạn có thể thay thế bằng ID thực tế trong bảng AUTHOR
//        newBook.setContent("Nội dung của cuốn sách này...");
//        // Không cần setCreatedat nếu bạn muốn tự động lấy ngày hiện tại
//        newBook.setImagesbook("https://vcdn1-giaitri.vnecdn.net/2018/03/20/co-gai-den-tu-hom-qua3-2984-14-6777-2518-1521549733.jpg?w=460&h=0&q=100&dpr=2&fit=crop&s=u8TzC1ZXZ6dyPZfTp3J_3Q"); // Không có ảnh, đặt giá trị null
//
//        try {
//            // Gọi hàm insert để thêm sách vào cơ sở dữ liệu
//            bookDao.insert(newBook);
//            System.out.println("Đã chèn cuốn sách mới vào cơ sở dữ liệu.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Lỗi trong quá trình chèn sách.");
//        }
//    }

	@Override
	public void update(BookModel book) throws SQLException {
		String sql = "UPDATE BOOK SET title = ?, author_id = ?, content = ?, created_at = ?, images_book = ? "
				+ "WHERE book_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getAuthorid());
			ps.setString(3, book.getContent());
			ps.setDate(4, new java.sql.Date(book.getCreatedat().getTime()));
			ps.setString(5, book.getImagesbook());
			ps.setInt(6, book.getBookid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int bookId) throws SQLException {
		String sql = "DELETE FROM BOOK WHERE book_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BookModel> findAll() {
		List<BookModel> books = new ArrayList<>();
		String sql = "SELECT B.book_id, B.title, B.content, B.created_at, B.images_book, B.author_id, A.author_name "
				+ "FROM BOOK B " + "JOIN AUTHOR A ON B.author_id = A.author_id";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				BookModel book = new BookModel();
				book.setBookid(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthorid(rs.getInt("author_id"));
				book.setAuthorname(rs.getString("author_name"));
				book.setContent(rs.getString("content"));
				book.setCreatedat(rs.getDate("created_at"));
				book.setImagesbook(rs.getString("images_book"));
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

//	public static void main(String[] args) {
//		BookDaoImpl bookDao = new BookDaoImpl();
//
//		// Gọi phương thức findAll() để lấy tất cả các sách
//		List<BookModel> books = bookDao.findAll();
//
//		// Kiểm tra nếu danh sách sách không rỗng thì in ra
//		if (books != null && !books.isEmpty()) {
//			System.out.println("Danh sách các cuốn sách:");
//			for (BookModel book : books) {
//				// In từng thuộc tính của sách, nếu null thì in "null"
//				System.out.println("Book ID: " + (book.getBookid() != 0 ? book.getBookid() : "null"));
//				System.out.println("Title: " + (book.getTitle() != null ? book.getTitle() : "null"));
//				System.out.println("Author ID: " + (book.getAuthorid() != 0 ? book.getAuthorid() : "null"));
//				System.out.println("Author Name: " + (book.getAuthorname() != null ? book.getAuthorname() : "null"));
//				System.out.println("Content: " + (book.getContent() != null ? book.getContent() : "null"));
//				System.out.println("Created At: " + (book.getCreatedat() != null ? book.getCreatedat() : "null"));
//
//				// Kiểm tra và in thông tin URL ảnh sách (nếu có)
//				if (book.getImagesbook() != null && !book.getImagesbook().isEmpty()) {
//					System.out.println("Images Book URL: " + book.getImagesbook());
//				} else {
//					System.out.println("Images Book: null");
//				}
//
//				System.out.println("----------");
//			}
//		} else {
//			System.out.println("Không có sách nào trong cơ sở dữ liệu.");
//		}
//	}


//	    public static void main(String[] args) {
//	        // Tạo đối tượng BookDaoImpl
//	        BookDaoImpl bookDao = new BookDaoImpl();
//
//	        // Tiêu đề sách cần tìm
//	        String titleToSearch = "Nhật ký trong tù"; // Thay đổi tiêu đề theo sách bạn muốn tìm
//
//	        // Gọi phương thức findByTitle
//	        List<BookModel> books = bookDao.findByTitle(titleToSearch);
//
//	        // Kiểm tra và in kết quả
//	        if (books.isEmpty()) {
//	            System.out.println("Không tìm thấy sách nào với tiêu đề: " + titleToSearch);
//	        } else {
//	            System.out.println("Kết quả tìm kiếm cho tiêu đề: " + titleToSearch);
//	            for (BookModel book : books) {
//	                System.out.println("ID: " + book.getBookid());
//	                System.out.println("Tiêu đề: " + book.getTitle());
//	                System.out.println("Tác giả: " + book.getAuthorname());
//	                System.out.println("Nội dung: " + book.getContent());
//	                System.out.println("Ngày tạo: " + book.getCreatedat());
//	                System.out.println("Hình ảnh: " + book.getImagesbook());
//	                System.out.println("-----------------------------------");
//	            }
//	        }
//	    }

	@Override
	public List<BookModel> findByTitle(String title) {
		List<BookModel> books = new ArrayList<>();
	    String sql = "SELECT B.*, A.author_name FROM BOOK B " +
	                 "JOIN AUTHOR A ON B.author_id = A.author_id " +
	                 "WHERE B.title LIKE ?";
	    try {
	        conn = super.getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, "%" + title + "%");
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            BookModel book = new BookModel();
	            book.setBookid(rs.getInt("book_id"));
	            book.setTitle(rs.getString("title"));
	            book.setAuthorid(rs.getInt("author_id"));
	            book.setAuthorname(rs.getString("author_name"));
	            book.setContent(rs.getString("content"));
	            book.setCreatedat(rs.getDate("created_at"));
	            book.setImagesbook(rs.getString("images_book"));
	            books.add(book);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return books;
	}
}