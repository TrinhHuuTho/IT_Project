package vn.HiepKa.dao;

import java.sql.SQLException;
import java.util.List;

import vn.HiepKa.models.BookGenreModel;

public interface IBookGenreDao {

	List<Integer> findBooksByGenreId(int genreId);

	List<Integer> findGenresByBookId(int bookId);

	void delete(int bookId, int genreId) throws SQLException;

	void insert(BookGenreModel bookGenre) throws SQLException;

}
