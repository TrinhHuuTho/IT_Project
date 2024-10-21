package vn.HiepKa.services;

import java.util.List;

import vn.HiepKa.models.BookModel;

public interface IBookService {
	    BookModel findById(int bookid);
	    List<BookModel> findAll();
	    void insert(BookModel book) throws Exception;
	    void update(BookModel book) throws Exception;
	    void delete(int bookid) throws Exception;
}
