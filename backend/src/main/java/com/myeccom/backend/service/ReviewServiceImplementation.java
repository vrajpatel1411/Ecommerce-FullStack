package com.myeccom.backend.service;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Product;
import com.myeccom.backend.model.Review;
import com.myeccom.backend.model.User;
import com.myeccom.backend.repository.ProductRepository;
import com.myeccom.backend.repository.ReviewRepository;
import com.myeccom.backend.request.ReviewRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private ReviewRepository reviewRepository;

    private ProductService productService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository,ProductService productService){
        this.productService=productService;
        this.reviewRepository=reviewRepository;
    }

    @Override
    public Review createReview(ReviewRequest req, User user) throws productException {
        Product product=productService.findProductById(req.getProductId());
        Review review=new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
