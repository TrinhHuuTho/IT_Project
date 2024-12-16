package vn.HiepKa.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.models.ReviewModel;
import vn.HiepKa.services.impl.ReviewServiceImpl;

@WebServlet(urlPatterns = { "/review" })
public class ReviewController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ReviewServiceImpl reviewService;

	@Override
	public void init() throws ServletException {
		super.init();
		// Khởi tạo service
		reviewService = new ReviewServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    System.out.println("ReviewController doGet called");
	    try {
	        int bookId = Integer.parseInt(req.getParameter("bookId"));
	        System.out.println("Received bookId: " + bookId);

	        ReviewModel reviewSummary = reviewService.getReviewSummary(bookId);
	        System.out.println("Review Summary: " + reviewSummary);

	        if (reviewSummary != null) {
	            req.setAttribute("averageRating", reviewSummary.getAverageRating());
	            req.setAttribute("totalReviews", reviewSummary.getTotalReviews());
	        } else {
	            req.setAttribute("averageRating", 0);
	            req.setAttribute("totalReviews", 0);
	        }

	        req.getRequestDispatcher("/views/story.jsp").forward(req, resp);
	    } catch (NumberFormatException e) {
	        System.err.println("Invalid bookId");
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid book ID");
	    }
	}

}
