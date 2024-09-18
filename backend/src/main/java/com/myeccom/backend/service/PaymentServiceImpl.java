package com.myeccom.backend.service;

import com.myeccom.backend.model.Order;
import com.myeccom.backend.response.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService  {


    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    public PaymentResponse createPaymentLink(Order order) throws StripeException {
        Stripe.apiKey=stripeSecretKey;
        System.out.println(order.getId());
        SessionCreateParams params=SessionCreateParams.
                builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setClientReferenceId(order.getId().toString())
                .setSuccessUrl("http://localhost:5173/payment/success/"+order.getId())
                .setCancelUrl("http://localhost:5173/payment/failure/"+order.getId())
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(
                                SessionCreateParams
                                        .LineItem
                                        .PriceData
                                        .builder()
                                        .setCurrency("CAD")
                                        .setUnitAmount((long) order.getDiscountedPrice()*100)
                                        .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder().setName("Vraj ECommerce").build()).build()
                        ).build()
                ).build();

        Session session=Session.create(params);


        PaymentResponse paymentResponse=new PaymentResponse();
        paymentResponse.setPaymentUrl(session.getUrl());
        System.out.println("Printing the URL =>"+paymentResponse.getPaymentUrl());
        return paymentResponse;
    }
}
