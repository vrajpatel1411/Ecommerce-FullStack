package com.myeccom.backend.repository;

import com.myeccom.backend.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query("SELECT R FROM Rating R WHERE R.product.id=:productId")
    public List<Rating> getProductsRating(@Param("productId") Long productId );
}
