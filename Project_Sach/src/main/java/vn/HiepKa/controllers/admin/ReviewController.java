package vn.HiepKa.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.models.ReviewModel;
import vn.HiepKa.services.IReviewService;
import vn.HiepKa.services.impl.ReviewServiceImpl;
@WebServlet(urlPatterns = { "/admin/review", "/admin/review/delete" })
public class ReviewController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IReviewService reviewService = new ReviewServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/admin/review")) {
			List<ReviewModel> review = reviewService.findAll();
			req.setAttribute("review", review);
			req.getRequestDispatcher("/views/admin/review.jsp").forward(req, resp);
		}
		if (url.contains("/admin/review/delete")) {
            // Lấy tham số reviewId từ request
            String reviewIdStr = req.getParameter("id");
            System.out.println("Review ID: " + reviewIdStr);  // Xuất giá trị reviewId ra console để kiểm tra

            if (reviewIdStr != null && !reviewIdStr.isEmpty()) {
                try {
                    reviewService.deleteReview(Integer.parseInt(reviewIdStr));  // Gọi service để xóa đánh giá
                    resp.sendRedirect(req.getContextPath() + "/admin/review");  // Chuyển hướng về danh sách đánh giá sau khi xóa
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid review ID format.");
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting review.");
                }
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Review ID is missing.");
            }
        }
	}
}
