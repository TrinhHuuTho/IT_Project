package vn.HiepKa.dao;

import java.sql.SQLException;
import java.util.List;

import vn.HiepKa.models.ReviewModel;

public interface IReviewDao {

	ReviewModel getReviewSummaryByBookId(int bookId);

	List<ReviewModel> getTopRatedBooks(String timePeriod);

	List<ReviewModel> findAll();

	void delete(int reviewId) throws SQLException;
	

}
