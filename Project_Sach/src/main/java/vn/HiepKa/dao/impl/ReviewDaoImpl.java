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
	    String sql = "SELECT TOP 10 b.book_id, b.title, " +
	                 "(SELECT STRING_AGG(g.genre_name, ', ') WITHIN GROUP (ORDER BY g.genre_name) " +
	                 " FROM BOOKGENRE bg INNER JOIN GENRE g ON bg.genre_id = g.genre_id WHERE bg.book_id = b.book_id) AS genres, " +
	                 "ROUND(AVG(r.rating), 2) AS average_rating, COUNT(r.review_id) AS total_reviews " +
	                 "FROM BOOK b " +
	                 "JOIN REVIEW r ON b.book_id = r.book_id ";

	    // Thêm điều kiện WHERE dựa trên timePeriod
	    if ("month".equals(timePeriod)) {
	        sql += "WHERE MONTH(r.created_at_review) = MONTH(GETDATE()) ";
	    } else if ("year".equals(timePeriod)) {
	        sql += "WHERE YEAR(r.created_at_review) = YEAR(GETDATE()) ";
	    }

	    // Phần còn lại của câu SQL
	    sql += "GROUP BY b.book_id, b.title " +
	           "ORDER BY average_rating DESC, total_reviews DESC";

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


	public static void main(String[] args) {
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

		IReviewDao mainTest = new ReviewDaoImpl();
		String timePeriod = "month"; // Thử với "month", "year", hoặc null
		List<ReviewModel> topRatedBooks = mainTest.getTopRatedBooks(timePeriod);

		// In kết quả ra console
		System.out.println("Top Rated Books for Time Period: " + timePeriod);
		for (ReviewModel book : topRatedBooks) {
			System.out.println("Book ID: " + book.getBookId());
			System.out.println("Title: " + book.getBookTitle());
			System.out.println("Genres: " + book.getGenres());
			System.out.println("Average Rating: " + book.getAverageRating());
			System.out.println("Total Reviews: " + book.getTotalReviews());
			System.out.println("--------------------------------------");

		}
	}

}
