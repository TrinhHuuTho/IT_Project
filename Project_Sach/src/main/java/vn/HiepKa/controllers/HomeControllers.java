package vn.HiepKa.controllers;

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

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/home" })
public class HomeControllers extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IBookService bookService = new BookService();
    private IGenreService genreService = new GenreServiceImpl();
    private IBookGenreService bookGenreService = new BookGenreServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy danh sách thể loại
        List<GenreModel> genres = genreService.getAllGenres();
        req.setAttribute("genres", genres);

        // Lấy genreId từ request
        String genreIdParam = req.getParameter("genreId");
        List<BookModel> hotBooks; // Sách trong danh sách thư viện (có filter)
        List<BookModel> newBooks; // Sách mới (không có filter)

        if (genreIdParam != null && !genreIdParam.isEmpty()) {
            int genreId = Integer.parseInt(genreIdParam);

            // Lấy danh sách bookId từ bảng BookGenre theo genreId
            List<Integer> bookIds = bookGenreService.getBooksByGenreId(genreId);

            // Lấy danh sách BookModel từ bookIds
            hotBooks = bookService.findBooksByIds(bookIds);
            req.setAttribute("selectedGenreId", genreId);
        } else {
            // Nếu không có genreId, lấy tất cả sách
            hotBooks = bookService.findAll();
        }

        // Lấy danh sách sách mới (luôn cố định, không áp dụng filter)
        newBooks = bookService.findAll();

        // Gán dữ liệu vào request attribute
        req.setAttribute("hotBooks", hotBooks);
        req.setAttribute("newBooks", newBooks);

        // Chuyển hướng đến trang JSP
        req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
    }
}
