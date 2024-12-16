package vn.HiepKa.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.HiepKa.models.UserModel;

@WebFilter(urlPatterns = {"/user/*"})
public class UserFilter implements Filter{

	 @Override
	    public void init(FilterConfig fConfig) throws ServletException {
	        System.out.println("UserFilter init!");
	    }

	    @Override
	    public void destroy() {
	        System.out.println("UserFilter destroy!");
	    }
	
	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {

	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse resp = (HttpServletResponse) response;
	        HttpSession session = req.getSession();

	        // Kiểm tra người dùng trong session
	        UserModel user = (UserModel) session.getAttribute("account");

	        // Kiểm tra nếu người dùng là admin (giả sử roleid 1 là user)
	        if (user != null && user.getRoleid() == 1) {
	            chain.doFilter(request, response); // Cho phép truy cập nếu đúng quyền
	        } else {
	            // Nếu không có quyền admin, chuyển hướng về trang home
	            resp.sendRedirect(req.getContextPath() + "/home");
	        }
	    }
}
