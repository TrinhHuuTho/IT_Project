package vn.HiepKa.services;

import java.util.List;

import vn.HiepKa.models.GenreModel;

public interface IGenreService {

	void deleteGenre(int genreId);

	void updateGenre(GenreModel genre);

	void addGenre(GenreModel genre);

	GenreModel getGenreById(int genreId);

	List<GenreModel> getAllGenres();

}
