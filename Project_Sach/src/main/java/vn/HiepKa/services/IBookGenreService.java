package vn.HiepKa.services;

import java.util.List;

import vn.HiepKa.models.BookGenreModel;

public interface IBookGenreService {

	List<Integer> getBooksByGenreId(int genreId);

	List<Integer> getGenresByBookId(int bookId);

	void deleteBookGenre(int bookId, int genreId);

	void addBookGenre(BookGenreModel bookGenre);

}
