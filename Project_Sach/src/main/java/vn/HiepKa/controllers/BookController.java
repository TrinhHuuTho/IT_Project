package vn.HiepKa.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.models.GenreModel;
import vn.HiepKa.models.ReviewModel;
import vn.HiepKa.services.IBookGenreService;
import vn.HiepKa.services.IBookService;
import vn.HiepKa.services.IReviewService;
import vn.HiepKa.services.impl.BookGenreServiceImpl;
import vn.HiepKa.services.impl.BookService;
import vn.HiepKa.services.impl.ReviewServiceImpl;

@WebServlet(urlPatterns = { "/story", "/read" })
public class BookController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IBookService bookService = new BookService();
	private IBookGenreService bookGenreService = new BookGenreServiceImpl();
	private IReviewService reviewService = new ReviewServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // Lấy ID từ tham số query string
	    String idString = req.getParameter("id");
	    int id = 0;

	    if (idString != null) {
	        try {
	            id = Integer.parseInt(idString); // Chuyển đổi ID từ chuỗi sang số nguyên
	        } catch (NumberFormatException e) {
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book ID");
	            return;
	        }
	    }

	    // Tìm kiếm sách theo ID
	    BookModel book = bookService.findById(id);

	    // Tìm kiếm đánh giá theo Id sách
	    ReviewModel reviewSummary = reviewService.getReviewSummary(id);

	    // Truy vấn tất cả các loại top rated books
	    List<ReviewModel> topRatedBooksMonth = reviewService.getTopRatedBooks("month");
	    List<ReviewModel> topRatedBooksYear = reviewService.getTopRatedBooks("year");
	    List<ReviewModel> topRatedBooksAll = reviewService.getTopRatedBooks("all");

	    // Set các dữ liệu vào request
	    req.setAttribute("topRatedBooksMonth", topRatedBooksMonth);
	    req.setAttribute("topRatedBooksYear", topRatedBooksYear);
	    req.setAttribute("topRatedBooksAll", topRatedBooksAll);

	    if (reviewSummary != null) {
	        req.setAttribute("averageRating", reviewSummary.getAverageRating());
	        req.setAttribute("totalReviews", reviewSummary.getTotalReviews());
	    }

	    if (book != null) {
	        req.setAttribute("book", book);
	        List<GenreModel> genres = bookGenreService.getGenresByBookId(id);
	        req.setAttribute("genres", genres);
	        req.setAttribute("pdfFileName", book.getTitle());

	        req.getRequestDispatcher("/views/story.jsp").forward(req, resp);
	    } else {
	        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
	    }
	}


}
