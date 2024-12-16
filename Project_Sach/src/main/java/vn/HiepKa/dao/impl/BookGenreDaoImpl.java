package vn.HiepKa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import vn.HiepKa.configs.DBConnectSQL;
import vn.HiepKa.dao.IBookGenreDao;
import vn.HiepKa.models.BookGenreModel;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.models.GenreModel;
import vn.HiepKa.models.GenreWithBooksModel;

public class BookGenreDaoImpl extends DBConnectSQL implements IBookGenreDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<GenreWithBooksModel> findAllGenresWithBooks() throws SQLException {
	    List<GenreWithBooksModel> genreWithBooksList = new ArrayList<>();
	    String sql = "SELECT g.genre_id, g.genre_name, b.book_id, b.title " +
	                 "FROM GENRE g " +
	                 "LEFT JOIN BOOKGENRE bg ON g.genre_id = bg.genre_id " +
	                 "LEFT JOIN BOOK b ON bg.book_id = b.book_id " +
	                 "ORDER BY g.genre_name";

	    try {
	        conn = super.getConnection();
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();

	        GenreWithBooksModel currentGenre = null;

	        while (rs.next()) {
	            int genreId = rs.getInt("genre_id");
	            String genreName = rs.getString("genre_name");
	            int bookId = rs.getInt("book_id");
	            String bookTitle = rs.getString("title");

	            // Nếu thể loại mới thì tạo mới đối tượng GenreWithBooksModel
	            if (currentGenre == null || currentGenre.getGenreId() != genreId) {
	                if (currentGenre != null) {
	                    genreWithBooksList.add(currentGenre);  // Thêm thể loại cũ vào danh sách
	                }

	                // Tạo đối tượng GenreWithBooksModel mới cho thể loại hiện tại
	                currentGenre = new GenreWithBooksModel();
	                currentGenre.setGenreId(genreId);
	                currentGenre.setGenreName(genreName);
	                currentGenre.setBooks(new ArrayList<>());
	            }

	            // Thêm sách vào danh sách của thể loại
	            if (bookId != 0) {
	                BookModel book = new BookModel();
	                book.setBookid(bookId);
	                book.setTitle(bookTitle);
	                currentGenre.getBooks().add(book);
	            }
	        }

	        // Thêm genre cuối cùng vào danh sách
	        if (currentGenre != null) {
	            genreWithBooksList.add(currentGenre);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closeResources();
	    }
	    return genreWithBooksList;
	}

	public static void main(String[] args) {
        BookGenreDaoImpl dao = new BookGenreDaoImpl();

        try {
            // Test phương thức lấy danh sách thể loại với sách
            List<GenreWithBooksModel> genresWithBooks = dao.findAllGenresWithBooks();

            // In kết quả ra màn hình
            for (GenreWithBooksModel genre : genresWithBooks) {
                System.out.println("Genre: " + genre.getGenreName());
                for (BookModel book : genre.getBooks()) {
                    System.out.println("  - Book: " + book.getTitle());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public void insert(BookGenreModel bookGenre) throws SQLException {
		String sql = "INSERT INTO BOOKGENRE (book_id, genre_id) VALUES (?, ?)";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookGenre.getBookid());
			ps.setInt(2, bookGenre.getGenreId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}
	

	@Override
	public void delete(int bookId, int genreId) throws SQLException {
		String sql = "DELETE FROM BOOKGENRE WHERE book_id = ? AND genre_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setInt(2, genreId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}

	@Override
	public List<GenreModel> findGenresByBookId(int bookId) {
	    List<GenreModel> genres = new ArrayList<>();
	    String query = "SELECT g.* FROM GENRE g "
	                 + "JOIN BOOKGENRE bg ON g.genre_id = bg.genre_id "
	                 + "WHERE bg.book_id = ?";
	    try {
	        conn = super.getConnection(); // Thiết lập kết nối
	        ps = conn.prepareStatement(query); // Chuẩn bị câu truy vấn
	        ps.setInt(1, bookId);
	        rs = ps.executeQuery(); // Thực thi câu truy vấn
	        while (rs.next()) {
	            GenreModel genre = new GenreModel();
	            genre.setGenreid(rs.getInt("genre_id"));
	            genre.setGenreName(rs.getString("genre_name"));
	            genre.setDescribeGenre(rs.getString("describe_genre"));
	            genres.add(genre);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(); // Đóng tài nguyên sau khi hoàn tất
	    }
	    return genres;
	}



	@Override
	public List<Integer> findBooksByGenreId(int genreId) {
		List<Integer> bookIds = new ArrayList<>();
		String sql = "SELECT book_id FROM BOOKGENRE WHERE genre_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, genreId);
			rs = ps.executeQuery();
			while (rs.next()) {
				bookIds.add(rs.getInt("book_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return bookIds;
	}

	private void closeResources() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Test phương thức trong main
//	public static void main(String[] args) {
//	    IBookGenreDao bookGenreDao = new BookGenreDaoImpl();
//
//	    // Lấy danh sách thể loại của sách có ID 1
//	    List<GenreModel> genres = bookGenreDao.findGenresByBookId(1);
//	    if (genres.isEmpty()) {
//	        System.out.println("Không tìm thấy thể loại nào cho sách với ID 1.");
//	    } else {
//	        System.out.println("Thể loại của sách có ID 1:");
//	        for (GenreModel genre : genres) {
//	            System.out.println("- " + genre.getGenreName());
//	        }
//	    }
//	}

}
