package vn.HiepKa.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Date; // java.sql.Date được sử dụng
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.HiepKa.models.BookModel;
import vn.HiepKa.services.IBookService;
import vn.HiepKa.services.impl.BookServiceImpl;
import vn.HiepKa.utils.Constant;

@WebServlet(urlPatterns = { "/admin/home", "/admin/delete", "/admin/edit", "/admin/update" , "/admin/add", "/admin/insert" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IBookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        if (url.contains("/admin/home")) {
            List<BookModel> books = bookService.findAll();
            req.setAttribute("books", books);
            req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
        } else if (url.contains("delete")) {
            String id = req.getParameter("id");
            try {
                bookService.delete(Integer.parseInt(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else if (url.contains("edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            BookModel book = bookService.findById(id);
            req.setAttribute("book", book);
            req.getRequestDispatcher("/views/admin/book-edit.jsp").forward(req, resp);
        } else if (url.contains("add")) {
        	req.getRequestDispatcher("/views/admin/book-add.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (url.contains("update")) {
            try {
                // Lấy thông tin từ form
                String bookId = req.getParameter("bookid");
                String title = req.getParameter("title");
                String authorname = req.getParameter("authorname");
                String statusStr = req.getParameter("status");
                boolean status = "1".equals(statusStr);

                // Xử lý ngày tạo
                String createdAtStr = req.getParameter("createdAt");
                Date createdAt = Date.valueOf(createdAtStr); // Định dạng yyyy-MM-dd

                // Tạo đối tượng BookModel
                BookModel book = new BookModel();
                book.setBookid(Integer.parseInt(bookId));
                book.setTitle(title);
                book.setAuthorname(authorname);
                book.setStatus(status);
                book.setCreatedat(createdAt);

                // Lưu lại thông tin sách cũ
                BookModel oldBook = bookService.findById(Integer.parseInt(bookId));
                String oldImage = oldBook.getImagesbook();
                String oldContent = oldBook.getContent();

             // Xử lý file PDF
                String pdfFileName = "";
                Part contentPart = req.getPart("content"); // Nhận file PDF từ form
                if (contentPart != null && contentPart.getSize() > 0) {
                    String originalFileName = Paths.get(contentPart.getSubmittedFileName()).getFileName().toString();
                    String pdfPath = Constant.DIR + "/FilePdf/" + originalFileName;

                    // Kiểm tra nếu file đã tồn tại, thêm timestamp vào tên file
                    File pdfFile = new File(pdfPath);
                    if (pdfFile.exists()) {
                        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // Phần mở rộng
                        String baseName = originalFileName.substring(0, originalFileName.lastIndexOf(".")); // Tên không bao gồm phần mở rộng
                        pdfFileName = baseName + "_" + System.currentTimeMillis() + "." + ext; // Tên mới với timestamp
                        pdfPath = Constant.DIR + "/FilePdf/" + pdfFileName;
                    } else {
                        pdfFileName = originalFileName; // Nếu không trùng, giữ nguyên tên gốc
                    }

                    // Lưu file vào thư mục
                    contentPart.write(pdfPath);
                    book.setContent(pdfFileName); // Lưu tên file vào database
                } else {
                    book.setContent(oldContent); // Giữ lại file PDF cũ nếu không upload mới
                }

             // Xử lý hình ảnh
                String imageFileName = "";
                String imageUrl = req.getParameter("imageUrl"); // Nhận URL từ form nếu có
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    // Nếu có URL, lưu trực tiếp vào database
                    imageFileName = imageUrl;
                    book.setImagesbook(imageFileName);
                } else {
                    Part imagePart = req.getPart("imageBook"); // Nhận file ảnh từ form
                    if (imagePart != null && imagePart.getSize() > 0) {
                        String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                        String imagePath = Constant.DIR + "/Images/" + originalFileName;

                        // Kiểm tra nếu file đã tồn tại, thêm timestamp vào tên file
                        File imageFile = new File(imagePath);
                        if (imageFile.exists()) {
                            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // Phần mở rộng
                            String baseName = originalFileName.substring(0, originalFileName.lastIndexOf(".")); // Tên không bao gồm phần mở rộng
                            imageFileName = baseName + "_" + System.currentTimeMillis() + "." + ext; // Tên mới với timestamp
                            imagePath = Constant.DIR + "/Images/" + imageFileName;
                        } else {
                            imageFileName = originalFileName; // Nếu không trùng, giữ nguyên tên gốc
                        }

                        // Lưu file vào thư mục
                        imagePart.write(imagePath);
                        book.setImagesbook(imageFileName); // Lưu tên file vào database
                    } else {
                        book.setImagesbook(oldImage); // Giữ lại ảnh cũ nếu không upload mới
                    }
                }

                // Cập nhật thông tin sách
                bookService.update(book, authorname);

                // Chuyển hướng về danh sách sách
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            } catch (SQLException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra trong quá trình cập nhật sách.");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dữ liệu không hợp lệ.");
            }
        }
        else if (url.contains("insert")) {
            try {
                // Lấy thông tin từ form
                String title = req.getParameter("title");
                String authorname = req.getParameter("authorname");
                String statusStr = req.getParameter("status");
                boolean status = "1".equals(statusStr);

                // Xử lý ngày tạo
                String createdAtStr = req.getParameter("createdAt");
                Date createdAt = Date.valueOf(createdAtStr); // Định dạng yyyy-MM-dd

                // Tạo đối tượng BookModel
                BookModel book = new BookModel();
                book.setTitle(title);
                book.setAuthorname(authorname);
                book.setStatus(status);
                book.setCreatedat(createdAt);

                // Xử lý file PDF
                String pdfFileName = "";
                Part contentPart = req.getPart("content"); // Nhận file PDF từ form
                if (contentPart != null && contentPart.getSize() > 0) {
                    String originalFileName = Paths.get(contentPart.getSubmittedFileName()).getFileName().toString();
                    String pdfPath = Constant.DIR + "/FilePdf/" + originalFileName;

                    // Kiểm tra nếu file đã tồn tại, thêm timestamp vào tên file
                    File pdfFile = new File(pdfPath);
                    if (pdfFile.exists()) {
                        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // Phần mở rộng
                        String baseName = originalFileName.substring(0, originalFileName.lastIndexOf(".")); // Tên không bao gồm phần mở rộng
                        pdfFileName = baseName + "_" + System.currentTimeMillis() + "." + ext; // Tên mới với timestamp
                        pdfPath = Constant.DIR + "/FilePdf/" + pdfFileName;
                    } else {
                        pdfFileName = originalFileName; // Nếu không trùng, giữ nguyên tên gốc
                    }

                    // Lưu file vào thư mục
                    contentPart.write(pdfPath);
                    book.setContent(pdfFileName); // Lưu tên file vào database
                }

                // Xử lý hình ảnh
                String imageFileName = "";
                String imageUrl = req.getParameter("imageUrl"); // Nhận URL từ form nếu có
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    // Nếu có URL, lưu trực tiếp vào database
                    imageFileName = imageUrl;
                    book.setImagesbook(imageFileName);
                } else {
                    Part imagePart = req.getPart("imageBook"); // Nhận file ảnh từ form
                    if (imagePart != null && imagePart.getSize() > 0) {
                        String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                        String imagePath = Constant.DIR + "/Images/" + originalFileName;

                        // Kiểm tra nếu file đã tồn tại, thêm timestamp vào tên file
                        File imageFile = new File(imagePath);
                        if (imageFile.exists()) {
                            String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1); // Phần mở rộng
                            String baseName = originalFileName.substring(0, originalFileName.lastIndexOf(".")); // Tên không bao gồm phần mở rộng
                            imageFileName = baseName + "_" + System.currentTimeMillis() + "." + ext; // Tên mới với timestamp
                            imagePath = Constant.DIR + "/Images/" + imageFileName;
                        } else {
                            imageFileName = originalFileName; // Nếu không trùng, giữ nguyên tên gốc
                        }

                        // Lưu file vào thư mục
                        imagePart.write(imagePath);
                        book.setImagesbook(imageFileName); // Lưu tên file vào database
                    } else {
                    	// Nếu không có ảnh được upload và không có URL ảnh, sử dụng ảnh mặc định
                        imageFileName = "avata.jpg"; // Thay URL này bằng URL của ảnh mặc định của bạn
                        book.setImagesbook(imageFileName); // Lưu URL ảnh mặc định vào database
                    }
                }

                // Lưu vào cơ sở dữ liệu
                bookService.insert(book); // Dùng phương thức insert từ bookService để thêm sách

                // Chuyển hướng về trang danh sách sách
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            } catch (SQLException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra trong quá trình thêm sách.");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dữ liệuuuuuu không hợp lệ.");
            }
        }
    }
}
