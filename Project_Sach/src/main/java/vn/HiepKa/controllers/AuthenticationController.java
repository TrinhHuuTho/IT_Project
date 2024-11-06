package vn.HiepKa.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.HiepKa.models.UserModel;
import vn.HiepKa.services.IUserService;
import vn.HiepKa.services.impl.UserService;
import vn.HiepKa.utils.Constant;

@WebServlet(urlPatterns = { "/authentication/", "/authentication/login", "/authentication/signup",
		"/authentication/forgotpassword" })
public class AuthenticationController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// Hiển thị trang đăng nhập hoặc đăng ký dựa vào URL
		if (url.contains("/authentication/") || url.contains("/login") || url.contains("/signup")
				|| url.contains("/forgotpassword")) {
			req.getRequestDispatcher("/views/login-page.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("/login")) {
			// Xử lý đăng nhập
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String remember = req.getParameter("remember");

			boolean isRememberMe = "on".equals(remember);

			// Kiểm tra xem email và password có hợp lệ không
			if (email.isEmpty() || password.isEmpty()) {
				req.setAttribute("errorMessage", "Email hoặc mật khẩu không để trống");
				req.getRequestDispatcher("/views/login-page.jsp").forward(req, resp);
				return;
			}

			UserModel user = userService.login(email, password);
			if (user != null) {
				HttpSession session = req.getSession(true);
				session.setAttribute("account", user);

				if (isRememberMe) {
					Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, email);
					cookie.setMaxAge(30 * 60);
					resp.addCookie(cookie);
				}
				resp.sendRedirect(req.getContextPath() + "/waiting");
			} else {
				req.setAttribute("errorMessage", "Email hoặc mật khẩu không đúng");
				req.getRequestDispatcher("/views/login-page.jsp").forward(req, resp);
			}
		} else if (url.contains("/signup")) {
			// Xử lý đăng ký
			String email = req.getParameter("email");
			String fullName = req.getParameter("fullname");
			String password = req.getParameter("password");
			if (email.isEmpty() || fullName.isEmpty() || password.isEmpty()) {
				req.setAttribute("errorMessage", "Email, tài khoản hoặc mật khẩu không được để trống");
				req.getRequestDispatcher("/views/login-page.jsp").forward(req, resp);
				return;
			}
			// Kiểm tra xem đăng ký có thành công không
			boolean isRegistered = userService.register(email, fullName, password);

			if (isRegistered) {
				// Đăng ký thành công, chuyển hướng đến trang đăng nhập
				resp.sendRedirect(req.getContextPath() + "/authentication/login");
			} else {
				// Đăng ký không thành công, hiển thị thông báo lỗi
				req.setAttribute("errorMessage", "Email đã tồn tại hoặc có lỗi xảy ra.");
				req.getRequestDispatcher("/views/login-page.jsp").forward(req, resp);
			}
		}
	}
}