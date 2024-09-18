package com.myeccom.backend.service;

import com.myeccom.backend.model.Order;
import com.myeccom.backend.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
