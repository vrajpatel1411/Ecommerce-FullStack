package com.myeccom.backend.service;

import com.myeccom.backend.Exception.productException;
import com.myeccom.backend.model.Product;
import com.myeccom.backend.model.Rating;
import com.myeccom.backend.model.User;
import com.myeccom.backend.repository.RatingRepository;
import com.myeccom.backend.repository.UserRepository;
import com.myeccom.backend.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService{

    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository,ProductService productService){
        this.productService=productService;
        this.ratingRepository=ratingRepository;
    }
    @Override
    public Rating createRating(RatingRequest req, User user) throws productException {
        Product product=productService.findProductById(req.getProductId());
        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setRating(req.getRating());
        rating.setUser(user);
        rating.setCreateAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRatings(Long productId) {

        return ratingRepository.getProductsRating(productId);
    }
}
