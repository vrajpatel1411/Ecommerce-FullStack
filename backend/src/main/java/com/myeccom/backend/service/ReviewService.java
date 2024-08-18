package com.myeccom.backend.service;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Review;
import com.myeccom.backend.model.User;
import com.myeccom.backend.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest req, User user) throws productException;

    public List<Review> getReviewByProductId(Long productId);
}
