package vn.HiepKa.controllers.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import vn.HiepKa.models.GenreModel;
import vn.HiepKa.services.impl.GenreServiceImpl;

@WebServlet(urlPatterns = {"/user/genres"})
public class GenreController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private GenreServiceImpl genreService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy danh sách thể loại
        List<GenreModel> genres = genreService.getAllGenres();

        if (genres != null) {
            // Nếu tìm thấy thể loại, lưu vào attribute để truy cập trong JSP
            req.setAttribute("genres", genres);
            req.getRequestDispatcher("/views/user/genre.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Genres not found");
        }

        System.out.println("Genre details: " + genres);
    }
}
