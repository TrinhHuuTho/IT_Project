package vn.HiepKa.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.HiepKa.models.BookModel;
import vn.HiepKa.models.GenreModel;
import vn.HiepKa.services.IBookGenreService;
import vn.HiepKa.services.IBookService;
import vn.HiepKa.services.IGenreService;
import vn.HiepKa.services.impl.BookGenreServiceImpl;
import vn.HiepKa.services.impl.BookService;
import vn.HiepKa.services.impl.GenreServiceImpl;

@WebServlet(urlPatterns = { "/genreDetails" })
public class BookGenreController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private IBookGenreService bookGenreService = new BookGenreServiceImpl();
	private IBookService bookService = new BookService();
	private IGenreService genreService = new GenreServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy genreId từ request
		String genreIdString = req.getParameter("genreId");
		int genreId = 0;

		try {
			genreId = Integer.parseInt(genreIdString);
		} catch (NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid genre ID");
			return;
		}

		// Lấy GenreModel để hiển thị thông tin chi tiết thể loại
		GenreModel genre = genreService.getGenreById(genreId);

		// Kiểm tra nếu genre có tồn tại
		if (genre != null) {
			req.setAttribute("genre", genre);

			// Lấy danh sách bookId thuộc genreId
			List<Integer> bookIds = bookGenreService.getBooksByGenreId(genreId);

			// Tạo danh sách BookModel từ bookIds
			List<BookModel> books = new ArrayList<>();
			for (int bookId : bookIds) {
				BookModel book = bookService.findById(bookId);
				if (book != null) {
					books.add(book);
				}
			}

			// Set các attribute để truy cập trong JSP
			req.setAttribute("books", books);
			req.getRequestDispatcher("/views/genre.jsp").forward(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Genre not found");
		}
	}
}
