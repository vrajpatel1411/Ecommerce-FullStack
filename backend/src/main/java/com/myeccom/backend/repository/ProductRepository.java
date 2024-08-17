package com.myeccom.backend.repository;

import com.myeccom.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p "+
            "WHERE (:category='' OR p.category.name=:category) "+
            "And ((:minPrice is NULL and :maxPrice is NUll) OR (p.discountedPrice between :minPrice and :maxPrice)) "+
            "AND (:minDiscount is NUll or p.discountPercent >= :minDiscount ) "+
            "ORDER BY CASE wHEN :sort='price_low' then p.discountedPrice END ASC , " +
            "case when :sort='price_high' Then p.discountedPrice End DESC"  )
    public List<Product> filterProducts(@Param("category") String Category, @Param("minPrice") Integer minPrice,@Param("maxPrice") Integer maxPrice,@Param("minDiscount") Integer minDiscount,@Param("sort") String sort);
}
