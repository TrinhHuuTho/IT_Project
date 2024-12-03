package vn.HiepKa.controllers.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.services.IBookService;
import vn.HiepKa.services.impl.BookService;

@WebServlet(urlPatterns = { "/admin/read" })
public class BookController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IBookService bookService = new BookService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		// Lấy ID từ tham số query string
		String idString = req.getParameter("id");
		int id = 0;

		if (idString != null) {
			try {
				id = Integer.parseInt(idString); // Chuyển đổi ID từ chuỗi sang số nguyên
			} catch (NumberFormatException e) {
				// Xử lý nếu ID không hợp lệ
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book ID");
				return;
			}
		}

		// Tìm kiếm sách theo ID
		BookModel book = bookService.findById(id); // Giả sử phương thức này trả về một đối tượng BookModel

		if (book != null) {
			// Nếu tìm thấy sách, lưu vào attribute để truy cập trong JSP
			req.setAttribute("book", book);
			req.setAttribute("pdfFileName", book.getTitle());
			if ("/admin/read".equals(path)) {
				req.getRequestDispatcher("/views/admin/read.jsp").forward(req, resp);
			}
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
		}

		System.out.println("Book details: " + book);
	}

}
