package com.myeccom.backend.service;


import com.myeccom.backend.Exception.OrderException;
import com.myeccom.backend.model.Address;
import com.myeccom.backend.model.Order;
import com.myeccom.backend.model.User;

import java.util.List;

public interface OrderService {

    public Order createOrder(User user, Address shippingAddress);

    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order> userOrdersHistory(Long userId);

    public Order placeOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancelledOrder(Long orderId) throws OrderException;

    public List<Order> getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;
}
