package com.myeccom.backend.model;

public class PaymentDetails {
    private String paymentMethod;

    private String status;

    private String paymentId;

    private String stripePaymentLinkId;


    private String stripePaymentLinkReferenceId;

    private String stripePaymentLinkStatus;

    private String stripePaymentId;

    public PaymentDetails() {
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStripePaymentLinkId() {
        return stripePaymentLinkId;
    }

    public void setStripePaymentLinkId(String stripePaymentLinkId) {
        this.stripePaymentLinkId = stripePaymentLinkId;
    }

    public String getStripePaymentLinkReferenceId() {
        return stripePaymentLinkReferenceId;
    }

    public void setStripePaymentLinkReferenceId(String stripePaymentLinkReferenceId) {
        this.stripePaymentLinkReferenceId = stripePaymentLinkReferenceId;
    }

    public String getStripePaymentLinkStatus() {
        return stripePaymentLinkStatus;
    }

    public void setStripePaymentLinkStatus(String stripePaymentLinkStatus) {
        this.stripePaymentLinkStatus = stripePaymentLinkStatus;
    }

    public String getStripePaymentId() {
        return stripePaymentId;
    }

    public void setStripePaymentId(String stripePaymentId) {
        this.stripePaymentId = stripePaymentId;
    }

    public PaymentDetails(String paymentMethod, String status, String paymentId, String stripePaymentLinkId, String stripePaymentLinkReferenceId, String stripePaymentLinkStatus, String stripePaymentId) {
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentId = paymentId;
        this.stripePaymentLinkId = stripePaymentLinkId;
        this.stripePaymentLinkReferenceId = stripePaymentLinkReferenceId;
        this.stripePaymentLinkStatus = stripePaymentLinkStatus;
        this.stripePaymentId = stripePaymentId;
    }
}