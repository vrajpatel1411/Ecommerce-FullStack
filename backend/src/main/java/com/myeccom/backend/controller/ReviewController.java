package com.myeccom.backend.controller;

import com.myeccom.backend.Exception.UserException;
import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Review;
import com.myeccom.backend.model.User;
import com.myeccom.backend.request.ReviewRequest;
import com.myeccom.backend.service.ReviewService;
import com.myeccom.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req, @RequestHeader("Authorization") String jwt) throws UserException, productException {
        User user=userService.findUserProfileByJwt(jwt);
        Review review=reviewService.createReview(req,user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId
    ) throws UserException, productException{
        List<Review> reviews=reviewService.getReviewByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.CREATED);
    }
}
