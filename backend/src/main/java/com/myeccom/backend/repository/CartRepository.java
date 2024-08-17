package com.myeccom.backend.repository;

import com.myeccom.backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT C FROM Cart C WHERE C.user.id=:userId")
    public Cart findByUserId(@Param("userId") Long userId);
}
