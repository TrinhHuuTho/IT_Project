package vn.HiepKa.controllers.book;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.services.impl.BookService;

@WebServlet(urlPatterns = { "/users/story/*" })
public class BookController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy ID từ URL
        String pathInfo = req.getPathInfo();
        String idString = pathInfo != null && pathInfo.length() > 1 ? pathInfo.substring(1) : null;
     // Khởi tạo ID
        int id = 0;
        if (idString != null) {
            try {
                id = Integer.parseInt(idString); // Chuyển đổi ID từ chuỗi sang số nguyên
            } catch (NumberFormatException e) {
                // Xử lý nếu ID không hợp lệ (có thể log lỗi)
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
            req.getRequestDispatcher("/views/book/story.jsp").forward(req, resp); // Chuyển đến trang JSP
        } else {
            // Nếu không tìm thấy sách, trả về lỗi 404
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
        }
	}
}
