package vn.HiepKa.services;

import java.util.List;
import java.sql.SQLException;

import vn.HiepKa.models.ReviewModel;

public interface IReviewService {

	ReviewModel getReviewSummary(int bookId);

	List<ReviewModel> getTopRatedBooks(String timePeriod);
	
	List<ReviewModel> findAll();

	void deleteReview(int reviewId) throws SQLException;
}
