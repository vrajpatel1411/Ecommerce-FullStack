package com.myeccom.backend.repository;

import com.myeccom.backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {


    @Query("SELECT R FROM Review R where R.product.id=:productId")
    public List<Review> getAllProductsReview(@Param("productId") Long productId);

}
