package vn.HiepKa.models;

import java.io.Serializable;

public class BookGenreModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int genreId;
	private int bookid;

	public BookGenreModel() {
		super();
	}

	public BookGenreModel(int genreId, int bookid) {
		super();
		this.genreId = genreId;
		this.bookid = bookid;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	@Override
	public String toString() {
		return "BookGenreModel [genreId=" + genreId + ", bookid=" + bookid + "]";
	}

}
