package com.myeccom.backend.controller;

import com.myeccom.backend.Exception.OrderException;
import com.myeccom.backend.Exception.UserException;
import com.myeccom.backend.model.Address;
import com.myeccom.backend.model.Order;
import com.myeccom.backend.model.User;
import com.myeccom.backend.response.PaymentResponse;
import com.myeccom.backend.service.OrderService;
import com.myeccom.backend.service.PaymentService;
import com.myeccom.backend.service.UserService;
import com.stripe.exception.StripeException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress, @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Order order = orderService.createOrder(user, shippingAddress);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistory(
            @RequestHeader("Authorization") String jwt
     ) throws UserException{
        User user= userService.findUserProfileByJwt(jwt);
        List<Order> orders=orderService.userOrdersHistory(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.CREATED);

     }

     @GetMapping("/checkout/{orderId}")
     public ResponseEntity<PaymentResponse> getPaymentLink(@PathVariable("orderId") Long id,@RequestHeader("Authorization") String jwt ) throws OrderException,UserException, StripeException {
        Order order=orderService.findOrderById(id);
        PaymentResponse res= paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res,HttpStatus.OK);
     }

     @GetMapping("/{Id}")
     public ResponseEntity<Order> findOrderById(@PathVariable("Id") Long id,@RequestHeader("Authorization") String jwt) throws UserException, OrderException {

        Order order=orderService.findOrderById(id);
         return new ResponseEntity<>(order, HttpStatus.OK);
     }
}
