package vn.HiepKa.dao;

import java.sql.SQLException;
import java.util.List;

import vn.HiepKa.models.BookModel;

public interface IBookDao {
	// Tìm sách theo ID
    BookModel findById(int bookid);

    // Tìm tất cả sách
    List<BookModel> findAll();

    // Tìm sách theo tiêu đề
    List<BookModel> findByTitle(String title);

    // Thêm mới một cuốn sách
    void insert(BookModel book) throws SQLException, Exception;
    // Xóa sách theo ID
    void delete(int bookid) throws SQLException, Exception;
    
	void update(BookModel book, String authorName, String genreName) throws SQLException;

	List<BookModel> findBooksByIds(List<Integer> bookIds);


}