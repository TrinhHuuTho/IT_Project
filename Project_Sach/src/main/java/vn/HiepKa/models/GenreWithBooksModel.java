package vn.HiepKa.models;

import java.util.List;

public class GenreWithBooksModel {
    private int genreId;  // ID thể loại
    private String genreName;  // Tên thể loại
    private List<BookModel> books;  // Danh sách sách thuộc thể loại này

    // Getter và Setter
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
    }
}
