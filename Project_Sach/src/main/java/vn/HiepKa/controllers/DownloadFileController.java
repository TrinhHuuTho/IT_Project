
package vn.HiepKa.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.HiepKa.utils.Constant;
import org.apache.commons.io.IOUtils;

@WebServlet(urlPatterns = { "/image", "/readPdf" })
public class DownloadFileController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("/image")) {
			String fileName = req.getParameter("fname");
			File file = new File(Constant.DIR + "/Images/" + fileName);
			resp.setContentType("image/jpeg");
			if (file.exists()) {
				IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
			}
		} else if (url.contains("/readPdf")) {
			String pdfFileName = req.getParameter("pdfName");

			// Đường dẫn đến thư mục chứa PDF (thay đổi cho phù hợp)
			File pdfFile = new File(Constant.DIR + "/FilePdf/" + pdfFileName);

			// Đặt kiểu nội dung là PDF
			resp.setContentType("application/pdf");

			if (pdfFile.exists()) {
				// Sử dụng IOUtils để sao chép nội dung PDF vào response
				try (FileInputStream fis = new FileInputStream(pdfFile)) {
					IOUtils.copy(fis, resp.getOutputStream());
				}
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND, "PDF file not found");
			}
		}
	}
}