package vn.HiepKa.services.impl;

import java.sql.SQLException;
import java.util.List;

import vn.HiepKa.dao.IBookGenreDao;
import vn.HiepKa.dao.impl.BookGenreDaoImpl;
import vn.HiepKa.models.BookGenreModel;
import vn.HiepKa.models.GenreModel;
import vn.HiepKa.models.GenreWithBooksModel;
import vn.HiepKa.services.IBookGenreService;

public class BookGenreServiceImpl implements IBookGenreService {

	IBookGenreDao bookGenreDao = new BookGenreDaoImpl();

	@Override
	public void addBookGenre(BookGenreModel bookGenre) {
		try {
			bookGenreDao.insert(bookGenre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBookGenre(int bookId, int genreId) {
		try {
			bookGenreDao.delete(bookId, genreId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<GenreModel> getGenresByBookId(int bookId) {
		return bookGenreDao.findGenresByBookId(bookId);
	}
	
	@Override
	public List<Integer> getBooksByGenreId(int genreId) {
		return bookGenreDao.findBooksByGenreId(genreId);
	}

	@Override
	public List<GenreWithBooksModel> findAllGenresWithBooks() throws SQLException {
		return bookGenreDao.findAllGenresWithBooks();
	}
}
