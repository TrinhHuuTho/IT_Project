package vn.HiepKa.dao;

import java.sql.SQLException;
import java.util.List;

import vn.HiepKa.models.GenreModel;

public interface IGenreDao {

	List<GenreModel> findAll();

	void delete(int genreId) throws SQLException;

	void update(GenreModel genre) throws SQLException;

	void insert(GenreModel genre) throws SQLException;

	GenreModel findById(int genreId);



}
