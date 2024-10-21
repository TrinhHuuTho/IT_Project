package vn.HiepKa.controllers.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.services.impl.BookService;

@WebServlet(urlPatterns = { "/user/home" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lấy danh sách sách từ tầng service
		List<BookModel> books = bookService.findAll();

		// Set danh sách sách vào attribute để truy cập trong JSP
		req.setAttribute("books", books);
		req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
	}
}