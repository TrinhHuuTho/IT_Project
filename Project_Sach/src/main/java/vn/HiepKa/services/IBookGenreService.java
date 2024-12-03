package vn.HiepKa.services;

import java.util.List;

import vn.HiepKa.models.BookGenreModel;
import vn.HiepKa.models.GenreModel;

public interface IBookGenreService {

	List<Integer> getBooksByGenreId(int genreId);

	List<GenreModel> getGenresByBookId(int bookId);

	void deleteBookGenre(int bookId, int genreId);

	void addBookGenre(BookGenreModel bookGenre);

}
