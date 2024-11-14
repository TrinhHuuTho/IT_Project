package vn.HiepKa.models;

import java.io.Serializable;

public class GenreModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int genreId;
	private String genreName;
	private String describeGenre;

	public GenreModel() {
		super();
	}

	public GenreModel(int genreid, String genreName, String describeGenre) {
		super();
		this.genreId = genreid;
		this.genreName = genreName;
		this.describeGenre = describeGenre;
	}

	public int getGenreid() {
		return genreId;
	}

	public void setGenreid(int genreid) {
		this.genreId = genreid;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getDescribeGenre() {
		return describeGenre;
	}

	public void setDescribeGenre(String describeGenre) {
		this.describeGenre = describeGenre;
	}

	@Override
	public String toString() {
		return "GenreModel [genreid=" + genreId + ", genreName=" + genreName + ", describeGenre=" + describeGenre + "]";
	}

}
