package vn.HiepKa.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.services.impl.BookService;

@WebServlet(urlPatterns = { "/admin/home", "/admin/delete" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/admin/home")) {
			// Lấy danh sách sách từ tầng service
			List<BookModel> books = bookService.findAll();

			// Set danh sách sách vào attribute để truy cập trong JSP
			req.setAttribute("books", books);
			req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
		}else if(url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				bookService.delete(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/home");
		}
	}
}