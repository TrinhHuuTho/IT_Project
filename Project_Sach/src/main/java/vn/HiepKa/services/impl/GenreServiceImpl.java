package vn.HiepKa.services.impl;

import java.sql.SQLException;
import java.util.List;

import vn.HiepKa.dao.IGenreDao;
import vn.HiepKa.dao.impl.GenreDaoImpl;
import vn.HiepKa.models.GenreModel;
import vn.HiepKa.services.IGenreService;

public class GenreServiceImpl implements IGenreService {

	IGenreDao genreDao = new GenreDaoImpl();

	@Override
	public List<GenreModel> getAllGenres() {
		return genreDao.findAll();
	}

	@Override
	public GenreModel getGenreById(int genreId) {
		return genreDao.findById(genreId);
	}

	@Override
	public void addGenre(GenreModel genre) {
		try {
			genreDao.insert(genre);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void updateGenre(GenreModel genre) {
		try {
			genreDao.update(genre);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void deleteGenre(int genreId) {
		try {
			genreDao.delete(genreId);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}
