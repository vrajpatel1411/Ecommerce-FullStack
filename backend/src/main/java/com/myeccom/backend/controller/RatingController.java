package com.myeccom.backend.controller;

import com.myeccom.backend.Exception.UserException;
import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Rating;
import com.myeccom.backend.model.User;
import com.myeccom.backend.request.RatingRequest;
import com.myeccom.backend.service.RatingService;
import com.myeccom.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, productException {
        User user=userService.findUserProfileByJwt(jwt);
        Rating rating=ratingService.createRating(req,user);
        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId,
                                                          @RequestHeader("Authorization") String jwt) throws UserException,productException{
        User user=userService.findUserProfileByJwt(jwt);
        List<Rating> ratings=ratingService.getProductRatings(productId);
        return new ResponseEntity<>(ratings,HttpStatus.CREATED);
    }
}
