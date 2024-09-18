package com.myeccom.backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myeccom.backend.Exception.OrderException;
import com.myeccom.backend.model.Order;
import com.myeccom.backend.model.PaymentDetails;
import com.myeccom.backend.repository.OrderRepository;
import com.myeccom.backend.service.OrderService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("/stripe/webhook")
public class StripeWebhookController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public OrderService orderService;


    @Autowired
    public OrderRepository orderRepository;

    @PostMapping("/")
    public ResponseEntity<String> handleStripeEvent(HttpServletRequest request,@RequestHeader("Stripe-Signature") String signatureHeader) throws StripeException, OrderException {

        String payload;
        try (Scanner scanner = new Scanner(request.getInputStream(), "UTF-8")) {
            payload = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error reading request");
        }

        Event event;
        try {
            event = Webhook.constructEvent(payload, signatureHeader, "whsec_QEKWfsghxg9VKamGNrnmTDHmyO8o5iSh");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Signature");
        }

        // Log the entire event data for debugging
        System.out.println("Event Data: " + payload);
        if (event.getType().equals("checkout.session.completed")) {
            try {
                // Manually parse JSON payload
                JsonNode jsonNode = objectMapper.readTree(payload);
                JsonNode sessionNode = jsonNode.path("data").path("object");

                // Extract session details
                String sessionId = sessionNode.path("id").asText();
                String customerId = sessionNode.path("customer").asText();
                String paymentIntentId = sessionNode.path("payment_intent").asText();
                long orderId = sessionNode.path("client_reference_id").asLong();

                // Print and use extracted details
                System.out.println("Session ID: " + sessionId);
                System.out.println("Customer ID: " + customerId);
                System.out.println("Payment Intent ID: " + paymentIntentId);
                System.out.println("Order ID: " + orderId);

                Order order=orderService.findOrderById(orderId);
                PaymentDetails paymentDetails=new PaymentDetails();
                paymentDetails.setPaymentMethod("CARD");
                paymentDetails.setStatus("SUCCESS");
                paymentDetails.setStripePaymentId(sessionId);
                paymentDetails.setStripePaymentLinkReferenceId(customerId);

                order.setPaymentDetails(paymentDetails);

                order.setOrderStatus("PLACED");

                orderRepository.save(order);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error parsing JSON");
            }

        }

        return ResponseEntity.ok("Received Stripe event");
    }
}
