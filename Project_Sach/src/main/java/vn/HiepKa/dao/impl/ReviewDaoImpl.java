package vn.HiepKa.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.HiepKa.configs.AzureConnectSQL;
import vn.HiepKa.dao.IReviewDao;
import vn.HiepKa.models.ReviewModel;

public class ReviewDaoImpl extends AzureConnectSQL implements IReviewDao {
	private Connection conn = null;
	private PreparedStatement ps = null;

	@Override
	public void delete(int reviewId) throws SQLException {
		String sql = "DELETE FROM REVIEW WHERE review_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewId); 
			ps.executeUpdate(); // Thực thi câu lệnh xóa
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        
        // Giả sử bạn muốn xóa review có ID là 1
        int reviewIdToDelete = 13;

        try {
            // Gọi phương thức delete để xóa
            reviewDao.delete(reviewIdToDelete);
            System.out.println("Đánh giá có ID = " + reviewIdToDelete + " đã được xóa thành công.");
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa đánh giá: " + e.getMessage());
            e.printStackTrace();
        }
    }

	@Override
	public List<ReviewModel> findAll() {
		List<ReviewModel> reviews = new ArrayList<>();
		// Câu truy vấn với JOIN giữa REVIEW, USER và BOOK
		String sql = "SELECT r.review_id, r.users_id, r.book_id, r.rating, r.comment, r.created_at_review, "
				+ "u.username, b.title " + "FROM REVIEW r " + "JOIN USERS u ON r.users_id = u.users_id " + // Kết nối
																											// bảng USER
				"JOIN BOOK b ON r.book_id = b.book_id"; // Kết nối bảng BOOK

		try (Connection conn = super.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			// Duyệt qua kết quả truy vấn và tạo đối tượng ReviewModel
			while (rs.next()) {
				ReviewModel review = new ReviewModel();
				review.setReviewId(rs.getInt("review_id"));
				review.setUserId(rs.getInt("users_id"));
				review.setBookId(rs.getInt("book_id"));
				review.setRating(rs.getInt("rating"));
				review.setComment(rs.getString("comment"));
				review.setCreatedAtReview(rs.getDate("created_at_review"));
				review.setBookTitle(rs.getString("title")); // Lấy tên sách từ bảng BOOK
				review.setUsername(rs.getString("username")); // Lấy username từ bảng USER

				// Thêm đối tượng vào danh sách
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reviews;
	}

	@Override
	public ReviewModel getReviewSummaryByBookId(int bookId) {
		String sql = "SELECT ROUND(AVG(rating), 2) AS average_rating, COUNT(*) AS total_reviews "
				+ "FROM REVIEW WHERE book_id = ?";
		try (Connection conn = super.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, bookId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					double averageRating = rs.getDouble("average_rating");
					int totalReviews = rs.getInt("total_reviews");
					return new ReviewModel(averageRating, totalReviews);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ReviewModel(0, 0); // Đảm bảo trả về một đối tượng mặc định khi không có kết quả
	}

	@Override
	public List<ReviewModel> getTopRatedBooks(String timePeriod) {
		String sql = "SELECT TOP 10 b.book_id, b.title, "
				+ "(SELECT STRING_AGG(g.genre_name, ', ') WITHIN GROUP (ORDER BY g.genre_name) "
				+ " FROM BOOKGENRE bg INNER JOIN GENRE g ON bg.genre_id = g.genre_id WHERE bg.book_id = b.book_id) AS genres, "
				+ "ROUND(AVG(r.rating), 2) AS average_rating, COUNT(r.review_id) AS total_reviews " + "FROM BOOK b "
				+ "JOIN REVIEW r ON b.book_id = r.book_id ";

		// Thêm điều kiện WHERE dựa trên timePeriod
		if ("month".equals(timePeriod)) {
			sql += "WHERE MONTH(r.created_at_review) = MONTH(GETDATE()) ";
		} else if ("year".equals(timePeriod)) {
			sql += "WHERE YEAR(r.created_at_review) = YEAR(GETDATE()) ";
		}

		// Phần còn lại của câu SQL
		sql += "GROUP BY b.book_id, b.title " + "ORDER BY average_rating DESC, total_reviews DESC";

		// Chuẩn bị danh sách kết quả
		List<ReviewModel> topRatedBooks = new ArrayList<>();
		try (Connection conn = super.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				ReviewModel review = new ReviewModel();
				review.setBookId(rs.getInt("book_id"));
				review.setBookTitle(rs.getString("title"));
				review.setGenres(rs.getString("genres")); // Lấy danh sách thể loại
				review.setAverageRating(rs.getDouble("average_rating"));
				review.setTotalReviews(rs.getInt("total_reviews"));
				topRatedBooks.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topRatedBooks;
	}

//	public static void main(String[] args) {
//		// Khởi tạo ReviewDaoImpl
//		ReviewDaoImpl reviewDao = new ReviewDaoImpl();
//
//		// Test với một bookId cụ thể
//		int bookId = 1; // Thay bằng book_id có dữ liệu thực tế trong DB
//		ReviewModel reviewSummary = reviewDao.getReviewSummaryByBookId(bookId);
//
//		// Kiểm tra và in kết quả
//		if (reviewSummary != null) {
//			System.out.println("Book ID: " + bookId);
//			System.out.println("Average Rating: " + reviewSummary.getAverageRating());
//			System.out.println("Total Reviews: " + reviewSummary.getTotalReviews());
//		} else {
//			System.out.println("No review data found for Book ID: " + bookId);
//		}

//		IReviewDao mainTest = new ReviewDaoImpl();
//		String timePeriod = "month"; // Thử với "month", "year", hoặc null
//		List<ReviewModel> topRatedBooks = mainTest.getTopRatedBooks(timePeriod);
//
//		// In kết quả ra console
//		System.out.println("Top Rated Books for Time Period: " + timePeriod);
//		for (ReviewModel book : topRatedBooks) {
//			System.out.println("Book ID: " + book.getBookId());
//			System.out.println("Title: " + book.getBookTitle());
//			System.out.println("Genres: " + book.getGenres());
//			System.out.println("Average Rating: " + book.getAverageRating());
//			System.out.println("Total Reviews: " + book.getTotalReviews());
//			System.out.println("--------------------------------------");
//
//		}
//	}

}
