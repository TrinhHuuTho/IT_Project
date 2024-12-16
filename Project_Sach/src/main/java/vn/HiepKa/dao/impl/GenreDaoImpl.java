package vn.HiepKa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.HiepKa.configs.DBConnectSQL;
import vn.HiepKa.dao.IGenreDao;
import vn.HiepKa.models.GenreModel;

public class GenreDaoImpl extends DBConnectSQL implements IGenreDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public GenreModel findById(int genreId) {
		String sql = "SELECT * FROM GENRE WHERE genre_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, genreId);
			rs = ps.executeQuery();
			if (rs.next()) {
				GenreModel genre = new GenreModel();
				genre.setGenreid(rs.getInt("genre_id"));
				genre.setGenreName(rs.getString("genre_name"));
				genre.setDescribeGenre(rs.getString("describe_genre"));
				return genre;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(GenreModel genre) throws SQLException {
		String sql = "INSERT INTO GENRE (genre_name, describe_genre) VALUES (?, ?)";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, genre.getGenreName());
			ps.setString(2, genre.getDescribeGenre());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GenreModel genre) throws SQLException {
		String sql = "UPDATE GENRE SET genre_name = ?, describe_genre = ? WHERE genre_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, genre.getGenreName());
			ps.setString(2, genre.getDescribeGenre());
			ps.setInt(3, genre.getGenreid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int genreId) throws SQLException {
		String sql = "DELETE FROM GENRE WHERE genre_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, genreId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<GenreModel> findAll() {
		List<GenreModel> genres = new ArrayList<>();
		String sql = "SELECT * FROM GENRE";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GenreModel genre = new GenreModel();
				genre.setGenreid(rs.getInt("genre_id"));
				genre.setGenreName(rs.getString("genre_name"));
				genre.setDescribeGenre(rs.getString("describe_genre"));
				genres.add(genre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genres;
	}
	
	@Override
	public GenreModel getGenreByName(String genreName) {
	    String sql = "SELECT * FROM GENRE WHERE genre_name = ?";
	    try {
	        conn = super.getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, genreName);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            GenreModel genre = new GenreModel();
	            genre.setGenreid(rs.getInt("genre_id"));
	            genre.setGenreName(rs.getString("genre_name"));
	            genre.setDescribeGenre(rs.getString("describe_genre"));
	            return genre;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null; // Trả về null nếu không tìm thấy thể loại
	}
}
