package vn.HiepKa.models;

import java.io.Serializable;
import java.sql.Date;

public class ReviewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int reviewId; // ID của review
	private int userId; // ID của người dùng
	private int bookId; // ID của sách
	private int rating; // Đánh giá (1-5)
	private String comment; // Bình luận
	private Date createdAtReview; // Ngày tạo review

	// Các thuộc tính bổ sung để hiển thị summary
	private double averageRating; // Trung bình đánh giá
	private int totalReviews; // Tổng số lượt đánh giá
	private String bookTitle;
	private String genres;
	
	public ReviewModel() {
		super();
	}

	public ReviewModel(int reviewId, int userId, int bookId, int rating, String comment, Date createdAtReview,
			double averageRating, int totalReviews, String bookTitle, String genres) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.bookId = bookId;
		this.rating = rating;
		this.comment = comment;
		this.createdAtReview = createdAtReview;
		this.averageRating = averageRating;
		this.totalReviews = totalReviews;
		this.bookTitle = bookTitle;
		this.genres = genres;
	}

	public ReviewModel(double averageRating, int totalReviews) {
		this.averageRating = averageRating;
		this.totalReviews = totalReviews;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedAtReview() {
		return createdAtReview;
	}

	public void setCreatedAtReview(Date createdAtReview) {
		this.createdAtReview = createdAtReview;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public int getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "ReviewModel [reviewId=" + reviewId + ", userId=" + userId + ", bookId=" + bookId + ", rating=" + rating
				+ ", comment=" + comment + ", createdAtReview=" + createdAtReview + ", averageRating=" + averageRating
				+ ", totalReviews=" + totalReviews + ", bookTitle=" + bookTitle + ", genres=" + genres + "]";
	}

}
