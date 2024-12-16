package vn.HiepKa.services.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.HiepKa.dao.IBookDao;
import vn.HiepKa.dao.impl.BookDaoImpl;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.services.IBookService;

public class BookServiceImpl implements IBookService {

	IBookDao bookDao = new BookDaoImpl();

	@Override
	public BookModel findById(int bookid) {
		BookModel book = bookDao.findById(bookid);
		if (book != null) {
			checkIsNew(book);
		}
		return book;
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
	public void update(BookModel book, String authorName, String genreName) throws SQLException {
		try {
			bookDao.update(book, authorName, genreName); // Gọi dao để cập nhật thông tin sách
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Lỗi khi cập nhật sách", e); // Ném lại ngoại lệ cho controller
		}
	}

	@Override
	public void delete(int bookid) throws Exception {
		BookModel book = new BookModel();
		book = bookDao.findById(bookid);
		if (book != null) {
			bookDao.delete(bookid);
		}

	}

	@Override
	public List<BookModel> findBooksByIds(List<Integer> bookIds) {
		if (bookIds == null || bookIds.isEmpty()) {
			return List.of(); // Trả về danh sách rỗng nếu không có bookIds
		}
		List<BookModel> books = bookDao.findBooksByIds(bookIds);
		for (BookModel book : books) {
			checkIsNew(book);
		}
		return books;
	}

}