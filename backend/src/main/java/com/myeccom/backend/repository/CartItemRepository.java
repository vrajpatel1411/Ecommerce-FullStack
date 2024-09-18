package com.myeccom.backend.repository;

import com.myeccom.backend.model.Cart;
import com.myeccom.backend.model.CartItem;
import com.myeccom.backend.model.Product;
import com.myeccom.backend.model.Size;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query("Select cl from CartItem cl where cl.cart=:cart and cl.product=:product and cl.size=:size and cl.userId=:userId")
    public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product, @Param("size") String size,@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("Delete from CartItem cI where cI.cart=:cart")
    public void DeleteCartItem(@Param("cart") Cart cart);

}
