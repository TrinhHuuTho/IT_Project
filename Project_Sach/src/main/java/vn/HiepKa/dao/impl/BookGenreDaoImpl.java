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
	public List<Integer> findGenresByBookId(int bookId) {
		List<Integer> genreIds = new ArrayList<>();
		String sql = "SELECT genre_id FROM BOOKGENRE WHERE book_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs = ps.executeQuery();
			while (rs.next()) {
				genreIds.add(rs.getInt("genre_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return genreIds;
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

//		// Thêm liên kết mới giữa book và genre
//		try {
//			bookGenreDao.insert(new BookGenreModel(1, 1));
//			System.out.println("Đã thêm liên kết giữa sách và thể loại.");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		// Lấy danh sách genreId theo bookId
		List<Integer> genres = bookGenreDao.findGenresByBookId(1);
		System.out.println("Thể loại của sách có ID 1: " + genres);

		// Lấy danh sách bookId theo genreId
		List<Integer> books = bookGenreDao.findBooksByGenreId(1);
		System.out.println("Sách có thể loại ID 1: " + books);
	}
}
