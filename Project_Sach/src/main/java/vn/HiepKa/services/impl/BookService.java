package vn.HiepKa.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.HiepKa.dao.IBookDao;
import vn.HiepKa.dao.impl.BookDaoImpl;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.services.IBookService;

public class BookService implements IBookService {

	IBookDao bookDao = new BookDaoImpl();

	@Override
	public BookModel findById(int bookid) {
		return bookDao.findById(bookid);
	}

	@Override
    public List<BookModel> findAll() {
        List<BookModel> books = bookDao.findAll();
        
        // Đặt cờ `isNew` cho từng sách
        for (BookModel book : books) {
            checkIsNew(book);
        }

        return books;
    }
       
	public void checkIsNew(BookModel book) {
        // Tính ngày 6 tháng trước
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date sixMonthsAgo = calendar.getTime();

        // Kiểm tra ngày tạo
        if (book.getCreatedat() != null && book.getCreatedat().after(sixMonthsAgo)) {
            book.setIsNew(true);
        } else {
            book.setIsNew(false);
        }
    }

	@Override
	public void insert(BookModel book) throws Exception {
		try {
			bookDao.insert(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(BookModel book) throws Exception {
		try {
			bookDao.update(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int bookid) throws Exception {
		bookDao.delete(bookid);
	}

}