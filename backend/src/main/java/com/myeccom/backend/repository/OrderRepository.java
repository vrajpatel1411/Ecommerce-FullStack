package com.myeccom.backend.repository;

import com.myeccom.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("Select  o from orders o where o.user.id=:userId And (o.orderStatus in  ('PLACED', 'CONFIRMED', 'SHIPPED', 'DELIVERED'))")
    public List<Order> getUsersOrders(@Param("userId") Long userId);
}
