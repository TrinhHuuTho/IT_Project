package vn.HiepKa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.HiepKa.configs.AzureConnectSQL;
import vn.HiepKa.dao.IBookGenreDao;
import vn.HiepKa.models.BookGenreModel;
import vn.HiepKa.models.GenreModel;

public class BookGenreDaoImpl extends AzureConnectSQL implements IBookGenreDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public void insert(BookGenreModel bookGenre) throws SQLException {
		String sql = "INSERT INTO BOOKGENRE (book_id, genre_id) VALUES (?, ?)";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookGenre.getBookid());
			ps.setInt(2, bookGenre.getGenreId());
			ps.executeUpdate();
		} catch (SQLException e) {
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
		} catch (SQLException e) {
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
	    } catch (SQLException e) {
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
		} catch (SQLException e) {
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
	public static void main(String[] args) {
	    IBookGenreDao bookGenreDao = new BookGenreDaoImpl();

	    // Lấy danh sách thể loại của sách có ID 1
	    List<GenreModel> genres = bookGenreDao.findGenresByBookId(1);
	    if (genres.isEmpty()) {
	        System.out.println("Không tìm thấy thể loại nào cho sách với ID 1.");
	    } else {
	        System.out.println("Thể loại của sách có ID 1:");
	        for (GenreModel genre : genres) {
	            System.out.println("- " + genre.getGenreName());
	        }
	    }
	}

}
