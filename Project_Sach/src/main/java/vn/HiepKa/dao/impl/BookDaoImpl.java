package vn.HiepKa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import vn.HiepKa.configs.DBConnectSQL;
import vn.HiepKa.dao.IBookDao;
import vn.HiepKa.models.BookModel;

public class BookDaoImpl extends DBConnectSQL implements IBookDao {

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
				book.setStatus(rs.getBoolean("status"));
				return book;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(BookModel book) throws SQLException {
	    String findAuthorSql = "SELECT author_id FROM AUTHOR WHERE author_name = ?";
	    String insertAuthorSql = "INSERT INTO AUTHOR (author_name) VALUES (?)";
	    String findGenreSql = "SELECT genre_id FROM GENRE WHERE genre_name = ?";
	    String insertGenreSql = "INSERT INTO GENRE (genre_name) VALUES (?)";
	    String insertBookSql = "INSERT INTO BOOK (title, author_id, content, created_at, images_book) VALUES (?, ?, ?, ?, ?)";
	    String insertBookGenreSql = "INSERT INTO BOOKGENRE (book_id, genre_id) VALUES (?, ?)";
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        conn = super.getConnection();
	        // Kiểm tra tác giả đã tồn tại chưa
	        int authorId = -1;
	        ps = conn.prepareStatement(findAuthorSql);
	        ps.setString(1, book.getAuthorname());
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            authorId = rs.getInt("author_id");
	        } else {
	            // Nếu tác giả chưa tồn tại, thêm tác giả mới
	            ps.close();
	            ps = conn.prepareStatement(insertAuthorSql, PreparedStatement.RETURN_GENERATED_KEYS);
	            ps.setString(1, book.getAuthorname());
	            ps.executeUpdate();
	            rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                authorId = rs.getInt(1);
	            }
	        }
	        // Chèn sách vào bảng BOOK
	        ps.close();
	        ps = conn.prepareStatement(insertBookSql, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setString(1, book.getTitle());
	        ps.setInt(2, authorId);
	        ps.setString(3, book.getContent());

	        // Gán ngày hiện tại nếu chưa có
	        if (book.getCreatedat() == null) {
	            book.setCreatedat(new java.sql.Date(new java.util.Date().getTime()));
	        }
	        ps.setDate(4, new java.sql.Date(book.getCreatedat().getTime()));
	        ps.setString(5, book.getImagesbook());
	        ps.executeUpdate();
	        
	        rs = ps.getGeneratedKeys();
	        int bookId = -1;
	        if (rs.next()) {
	            bookId = rs.getInt(1);
	        }
	        // Kiểm tra thể loại và chèn nếu cần
	        if (book.getGenreName() != null && !book.getGenreName().isEmpty()) {
	            ps.close();
	            ps = conn.prepareStatement(findGenreSql);
	            ps.setString(1, book.getGenreName());
	            rs = ps.executeQuery();
	            int genreId = -1;
	            if (rs.next()) {
	                // Nếu thể loại đã tồn tại, lấy genre_id
	                genreId = rs.getInt("genre_id");
	            } else {
	                // Nếu thể loại chưa tồn tại, thêm mới vào bảng GENRE
	                ps.close();
	                ps = conn.prepareStatement(insertGenreSql, PreparedStatement.RETURN_GENERATED_KEYS);
	                ps.setString(1, book.getGenreName());
	                ps.executeUpdate();
	                rs = ps.getGeneratedKeys();
	                if (rs.next()) {
	                    genreId = rs.getInt(1);
	                }
	            }
	            // Liên kết sách với thể loại trong bảng BOOKGENRE
	            ps.close();
	            ps = conn.prepareStatement(insertBookGenreSql);
	            ps.setInt(1, bookId);
	            ps.setInt(2, genreId);
	            ps.executeUpdate();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();  // Cần xử lý lỗi cụ thể hơn
	    } 
	}

	@Override
	public void update(BookModel book, String authorName, String genreName) throws SQLException {
	    String findAuthorSql = "SELECT author_id FROM AUTHOR WHERE author_name = ?";
	    String insertAuthorSql = "INSERT INTO AUTHOR (author_name) VALUES (?)";
	    String updateBookSql = "UPDATE BOOK SET title = ?, author_id = ?, content = ?, created_at = ?, images_book = ?, status = ? WHERE book_id = ?";
	    String findGenreSql = "SELECT genre_id FROM GENRE WHERE genre_name = ?";
	    String insertGenreSql = "INSERT INTO GENRE (genre_name) VALUES (?)";
	    String insertBookGenreSql = "INSERT INTO BOOKGENRE (book_id, genre_id) VALUES (?, ?)";
	    String deleteBookGenreSql = "DELETE FROM BOOKGENRE WHERE book_id = ?"; // Dùng để xóa liên kết cũ nếu cần

	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = super.getConnection();
	        // Kiểm tra xem tác giả đã tồn tại hay chưa
	        int authorId = -1;
	        ps = conn.prepareStatement(findAuthorSql);
	        ps.setString(1, authorName);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            // Nếu tác giả tồn tại, lấy ID
	            authorId = rs.getInt("author_id");
	        } else {
	            // Nếu tác giả không tồn tại, thêm mới
	            ps.close(); // Đóng PreparedStatement trước khi mở mới
	            ps = conn.prepareStatement(insertAuthorSql, PreparedStatement.RETURN_GENERATED_KEYS);
	            ps.setString(1, authorName);
	            ps.executeUpdate();

	            // Lấy ID tác giả vừa thêm
	            rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                authorId = rs.getInt(1);
	            }
	        }
	        // Kiểm tra thể loại
	        int genreId = -1;
	        ps = conn.prepareStatement(findGenreSql);
	        ps.setString(1, genreName);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            // Nếu thể loại tồn tại, lấy ID
	            genreId = rs.getInt("genre_id");
	        } else {
	            // Nếu thể loại chưa tồn tại, thêm mới
	            ps.close(); // Đóng PreparedStatement trước khi mở mới
	            ps = conn.prepareStatement(insertGenreSql, PreparedStatement.RETURN_GENERATED_KEYS);
	            ps.setString(1, genreName);
	            ps.executeUpdate();

	            // Lấy ID thể loại vừa thêm
	            rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                genreId = rs.getInt(1);
	            }
	        }
	        // Cập nhật sách
	        ps.close(); // Đóng PreparedStatement trước khi mở mới
	        ps = conn.prepareStatement(updateBookSql);
	        ps.setString(1, book.getTitle());               // Tiêu đề
	        ps.setInt(2, authorId);                         // ID tác giả
	        ps.setString(3, book.getContent());             // Nội dung
	        ps.setDate(4, new java.sql.Date(book.getCreatedat().getTime())); // Ngày tạo
	        ps.setString(5, book.getImagesbook());          // Hình ảnh
	        ps.setInt(6, book.isStatus() ? 1 : 0);          // Trạng thái
	        ps.setInt(7, book.getBookid());                 // ID sách
	        ps.executeUpdate();

	        // Xóa các thể loại cũ liên kết với sách (nếu có)
	        ps.close();
	        ps = conn.prepareStatement(deleteBookGenreSql);
	        ps.setInt(1, book.getBookid());
	        ps.executeUpdate();

	        // Liên kết sách với thể loại mới (có thể chỉ có 1 thể loại, bạn có thể thay đổi nếu có nhiều thể loại)
	        ps.close();
	        ps = conn.prepareStatement(insertBookGenreSql);
	        ps.setInt(1, book.getBookid());  // ID sách
	        ps.setInt(2, genreId);            // ID thể loại
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
		String sql = "SELECT B.book_id, B.title, B.content, B.created_at, B.images_book, B.author_id, B.status, A.author_name "
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
				book.setStatus(rs.getBoolean("status"));
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
	
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
	            book.setStatus(rs.getBoolean("status"));
	            books.add(book);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return books;
	}

	@Override
	public List<BookModel> findBooksByIds(List<Integer> bookIds) {
	    StringBuilder sql = new StringBuilder("SELECT * FROM BOOK WHERE book_id IN (");
	    for (int i = 0; i < bookIds.size(); i++) {
	        sql.append("?");
	        if (i < bookIds.size() - 1) {
	            sql.append(",");
	        }
	    }
	    sql.append(")");

	    List<BookModel> books = new ArrayList<>();
	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql.toString())) {
	        for (int i = 0; i < bookIds.size(); i++) {
	            ps.setInt(i + 1, bookIds.get(i));
	        }
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                BookModel book = new BookModel();
	                book.setBookid(rs.getInt("book_id"));
	                book.setTitle(rs.getString("title"));
	                book.setImagesbook(rs.getString("images_book"));
	                book.setStatus(rs.getBoolean("status"));
	                books.add(book);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return books;
	}
}