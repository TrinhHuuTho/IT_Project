package vn.HiepKa.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.HiepKa.models.UserModel;

@WebServlet(urlPatterns = { "/waiting" })
public class WaitingController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		// Kiểm tra xem session có chứa tài khoản không
		if (session != null && session.getAttribute("account") != null) {
			// Lấy thông tin người dùng từ session
			UserModel u = (UserModel) session.getAttribute("account");

			// Set thuộc tính username cho request nếu cần sử dụng ở view
			session.setAttribute("username", u.getUsername());

			// Dựa trên roleId để chuyển hướng người dùng
			if (u.getRoleid() == 2) {
				// Nếu là admin, chuyển đến trang admin
				resp.sendRedirect(req.getContextPath() + "/admin/home");
			} else if (u.getRoleid() == 1) {
				// Nếu là user, chuyển đến trang người dùng
				resp.sendRedirect(req.getContextPath() + "/user/home");
			} else {
				// Nếu roleId không hợp lệ, chuyển hướng về trang đăng nhập
				resp.sendRedirect(req.getContextPath() + "/authentication/");
			}
		} else {
			// Nếu không có session hoặc không đăng nhập, chuyển hướng đến trang đăng nhập
			resp.sendRedirect(req.getContextPath() + "/authentication/");
		}
	}
}
