package vn.HiepKa.services;

import java.sql.SQLException;
import java.util.List;

import vn.HiepKa.models.BookModel;

public interface IBookService {
	BookModel findById(int bookid);

	List<BookModel> findAll();

	void insert(BookModel book) throws Exception;

	void delete(int bookid) throws Exception;

	void update(BookModel book, String authorName) throws SQLException;
	
	List<BookModel> findBooksByIds(List<Integer> bookIds);
}