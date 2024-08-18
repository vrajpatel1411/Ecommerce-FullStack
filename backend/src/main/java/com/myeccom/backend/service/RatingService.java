package com.myeccom.backend.service;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Rating;
import com.myeccom.backend.model.User;
import com.myeccom.backend.request.RatingRequest;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest req , User user) throws productException;

    public List<Rating> getProductRatings(Long productId);
}
