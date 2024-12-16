package vn.HiepKa.controllers.user;

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
import vn.HiepKa.services.IGenreService;
import vn.HiepKa.services.IReviewService;
import vn.HiepKa.services.impl.BookGenreServiceImpl;
import vn.HiepKa.services.impl.BookServiceImpl;
import vn.HiepKa.services.impl.GenreServiceImpl;
import vn.HiepKa.services.impl.ReviewServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/user/home" })
public class HomeControllers extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IBookService bookService = new BookServiceImpl();
    private IGenreService genreService = new GenreServiceImpl();
    private IBookGenreService bookGenreService = new BookGenreServiceImpl();
    private IReviewService reviewService = new ReviewServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy danh sách thể loại
        List<GenreModel> genres = genreService.getAllGenres();
        req.setAttribute("genres", genres);

        // Lấy genreId từ request
        String genreIdParam = req.getParameter("genreId");
        List<BookModel> hotBooks;
        List<BookModel> newBooks;

        if (genreIdParam != null && !genreIdParam.isEmpty()) {
            int genreId = Integer.parseInt(genreIdParam);

            // Lấy danh sách bookId từ bảng BookGenre theo genreId
            List<Integer> bookIds = bookGenreService.getBooksByGenreId(genreId);

            // Lấy danh sách BookModel từ bookIds
            hotBooks = bookService.findBooksByIds(bookIds);
            req.setAttribute("selectedGenreId", genreId);
        } else {
            hotBooks = bookService.findAll();
        }

        // Đánh giá sách (averageRating, totalReviews)
        for (BookModel book : hotBooks) {
            ReviewModel reviewSummary = reviewService.getReviewSummary(book.getBookid());
            if (reviewSummary != null) {
                double averageRating = reviewSummary.getAverageRating();
                int totalReviews = reviewSummary.getTotalReviews();

                // Đánh dấu sách "Hot"
                book.setIsHot(averageRating >= 4.0 && totalReviews >= 2);             
            } else {
                book.setIsHot(false); // Không có đánh giá
            }
        }

        // Lấy danh sách sách mới
        newBooks = bookService.findAll();

        // Gán dữ liệu vào request attribute
        req.setAttribute("hotBooks", hotBooks);
        req.setAttribute("newBooks", newBooks);

        // Chuyển hướng đến trang JSP
        req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
    }

}
