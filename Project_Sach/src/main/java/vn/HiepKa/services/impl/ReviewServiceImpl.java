package vn.HiepKa.services.impl;

import java.util.List;

import vn.HiepKa.dao.IReviewDao;
import vn.HiepKa.dao.impl.ReviewDaoImpl;
import vn.HiepKa.models.ReviewModel;
import vn.HiepKa.services.IReviewService;

public class ReviewServiceImpl implements IReviewService{
	
	IReviewDao reviewDao = new ReviewDaoImpl();
	
	@Override
	public ReviewModel getReviewSummary(int bookId) {
	    return reviewDao.getReviewSummaryByBookId(bookId);
	}
	
	@Override
	public List<ReviewModel> getTopRatedBooks(String timePeriod) {
	    return reviewDao.getTopRatedBooks(timePeriod);
	}

}
