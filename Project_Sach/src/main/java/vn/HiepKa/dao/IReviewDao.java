package vn.HiepKa.dao;

import java.util.List;

import vn.HiepKa.models.ReviewModel;

public interface IReviewDao {

	ReviewModel getReviewSummaryByBookId(int bookId);

	List<ReviewModel> getTopRatedBooks(String timePeriod);

}
