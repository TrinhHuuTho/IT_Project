package vn.HiepKa.controllers.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.models.GenreWithBooksModel;
import vn.HiepKa.services.impl.BookGenreServiceImpl;

@WebServlet(urlPatterns = { "/admin/genre" })
public class BookGenreController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookGenreServiceImpl bookGenreService = new BookGenreServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<GenreWithBooksModel> genreWithBooks = bookGenreService.findAllGenresWithBooks();
            req.setAttribute("genreWithBooks", genreWithBooks);
            req.getRequestDispatcher("/views/admin/bookgenre.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching genres and books");
        }
    }
}
