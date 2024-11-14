package vn.HiepKa.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.models.GenreModel;
import vn.HiepKa.services.IBookService;
import vn.HiepKa.services.IGenreService;
import vn.HiepKa.services.impl.BookService;
import vn.HiepKa.services.impl.GenreServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/home" })
public class HomeControllers extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IBookService bookService = new BookService();
	private IGenreService genreService = new GenreServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lấy danh sách sách từ tầng service
		List<BookModel> books = bookService.findAll();

		// Set danh sách sách vào attribute để truy cập trong JSP
		req.setAttribute("books", books);
		
		// Lấy danh sách thể loại từ service
        List<GenreModel> genres = genreService.getAllGenres();
        
        // Đưa danh sách thể loại vào request attribute để JSP có thể sử dụng
        req.setAttribute("genres", genres);
        
		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}
}
