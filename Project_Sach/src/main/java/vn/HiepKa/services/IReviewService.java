package vn.HiepKa.services;

import java.util.List;

import vn.HiepKa.models.ReviewModel;

public interface IReviewService {

	ReviewModel getReviewSummary(int bookId);

	List<ReviewModel> getTopRatedBooks(String timePeriod);
	
}
