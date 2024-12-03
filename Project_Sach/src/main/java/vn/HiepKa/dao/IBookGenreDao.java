package vn.HiepKa.dao;

import java.sql.SQLException;
import java.util.List;

import vn.HiepKa.models.BookGenreModel;
import vn.HiepKa.models.GenreModel;

public interface IBookGenreDao {

	List<Integer> findBooksByGenreId(int genreId);
	
	List<GenreModel> findGenresByBookId(int bookId);

	void delete(int bookId, int genreId) throws SQLException;

	void insert(BookGenreModel bookGenre) throws SQLException;

}
