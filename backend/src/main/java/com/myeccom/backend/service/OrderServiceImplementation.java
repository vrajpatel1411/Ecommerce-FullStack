package com.myeccom.backend.service;

import com.myeccom.backend.Exception.OrderException;
import com.myeccom.backend.model.*;
import com.myeccom.backend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation implements OrderService{

    private OrderRepository orderRepository;

    private AddressRepository addressRepository;

    private UserRepository userRepository;

    private OrderItemService orderItemService;

    private OrderItemRepository orderItemRepository;

    private CartService cartService;


    public OrderServiceImplementation(OrderRepository orderRepository, CartService cartService,AddressRepository addressRepository, UserRepository userRepository,OrderItemService orderItemService,OrderItemRepository orderItemRepository){
        this.cartService=cartService;
        this.orderRepository=orderRepository;
        this.addressRepository=addressRepository;
        this.userRepository=userRepository;
        this.orderItemService=orderItemService;
        this.orderItemRepository=orderItemRepository;
    }

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        Address newAddress;

        // Check if the address is new or existing
        if (shippingAddress.getId() == null) {
            // New address, so save it
            shippingAddress.setUser(user);
            newAddress = addressRepository.save(shippingAddress);
            user.getAddress().add(newAddress); // Update user's address list
            userRepository.save(user); // Save the user with updated address list
        } else {
            // Existing address, so load it from the repository
            Optional<Address> existingAddress = addressRepository.findById(shippingAddress.getId());
            if (existingAddress.isEmpty()) {
                throw new RuntimeException("Address with ID " + shippingAddress.getId() + " does not exist.");
            }
            newAddress = existingAddress.get();
        }

        // Create order items from the cart
        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());

            // Save orderItem and add to the list
            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(createdOrderItem);
        }

        // Create the order
        Order createdOrder = new Order();
        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setDiscount(cart.getDiscount());
        createdOrder.setTotalItems(cart.getTotalltem());
        createdOrder.setShippingAddress(newAddress);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setStatus("PENDING");
        createdOrder.setCreatedAt(LocalDateTime.now());

        // Save the order
        Order savedOrder;
        try {
            savedOrder = orderRepository.save(createdOrder);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to save order.");
        }

        // Set the saved order reference in each order item
        for (OrderItem item : orderItems) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;
    }

//    @Override
//    public Order createOrder(User user, Address shippingAddress) {
//        System.out.println("Old address only");
//
//        Address newAddress=null;
//        // Check if the address is new or existing
//        if (shippingAddress.getId() == null) {
//            // New address, so save it
//            shippingAddress.setUser(user);
//            newAddress = addressRepository.save(shippingAddress);
//            user.getAddress().add(newAddress); // Update user's address list
//            userRepository.save(user); // Save the user with updated address list
//        } else {
//            // Existing address, so load it from the repository
//            Optional<Address> existingAddress = addressRepository.findById(shippingAddress.getId());
//            if (existingAddress.isEmpty()) {
//                throw new RuntimeException("Address with ID " + shippingAddress.getId() + " does not exist.");
//            }
//            newAddress = existingAddress.get();
//        }
//        Cart cart=cartService.findUserCart(user.getId());
//        List<OrderItem> orderItems=new ArrayList<>();
//        for(CartItem item:cart.getCartItems()){
//            OrderItem orderItem=new OrderItem();
//            orderItem.setPrice(item.getPrice());
//            orderItem.setProduct(item.getProduct());
//            orderItem.setQuantity(item.getQuantity());
//            orderItem.setSize(item.getSize());
//            orderItem.setUserId(item.getUserId());
//            orderItem.setDiscountedPrice(item.getDiscountedPrice());
//
//            OrderItem createdOrderItem=orderItemRepository.save(orderItem);
//
//            orderItems.add(createdOrderItem);
//        }
//
//        Order createdOrder=new Order();
//
//        createdOrder.setUser(user);
//        createdOrder.setOrderItems(orderItems);
//        createdOrder.setTotalPrice(cart.getTotalPrice());
//        createdOrder.setDiscountedPrice(cart.getTotalDiscountedPrice());
//        createdOrder.setDiscount(cart.getDiscount());
//        createdOrder.setTotalItems(cart.getTotalltem());
//
//
//        createdOrder.setShippingAddress(newAddress);
//        createdOrder.setOrderDate(LocalDateTime.now());
//        createdOrder.setOrderStatus("PENDING");
//        createdOrder.getPaymentDetails().setStatus("PENDING");
//        createdOrder.setCreatedAt(LocalDateTime.now());
//        System.out.println("order ------------------------------ -"+createdOrder.getShippingAddress().toString());
//        Order savedOrder=null;
//                try {
//                    savedOrder = orderRepository.save(createdOrder);
//                }
//                catch (Exception e){
//                    System.out.println(e.getMessage());
//                }
//        System.out.println("Saved Order -------- "+savedOrder);
//        for(OrderItem item:orderItems){
//            item.setOrder(savedOrder);
//            orderItemRepository.save(item);
//        }
//
//        return savedOrder;
//    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException{
        Optional<Order> opt=orderRepository.findById(orderId);

        if(opt.isPresent()){
            return opt.get();
        }

        throw new OrderException("Order  does not exist with id : "+orderId);
    }

    @Override
    public List<Order> userOrdersHistory(Long userId) {
        System.out.println(userId);
        List<Order> orders=orderRepository.getUsersOrders(userId);

        return orders;
    }

    @Override
    public Order placeOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setStatus("Completed");
        return orderRepository.save(order);
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public Order cancelledOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException{
        Order order=findOrderById(orderId);
        orderRepository.deleteById(order.getId());
    }
}
