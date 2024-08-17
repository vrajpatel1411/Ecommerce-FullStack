package com.myeccom.backend.service;

import com.myeccom.backend.Exception.OrderException;
import com.myeccom.backend.model.Address;
import com.myeccom.backend.model.Order;
import com.myeccom.backend.model.User;
import com.myeccom.backend.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{

    private CartRepository cartRepository;

    private CartService cartService;

    private ProductService productService;

    public OrderServiceImplementation(CartRepository cartRepository, CartService cartService, ProductService productService){
        this.cartService=cartService;
        this.cartRepository=cartRepository;
        this.productService=productService;
    }
    @Override
    public Order createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> userOrdersHistory(Long userId) {
        return null;
    }

    @Override
    public Order placeOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancelledOrder(Long orderId) throws OrderException {
        return null;
    }
}
