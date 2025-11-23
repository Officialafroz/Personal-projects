package com.elevata.gsrtc.enums;

public enum PaymentStatus {
    PENDING("pending"),
    SUCCESS("success"),
    REFUNDED("refunded"),
    FAILED("failed");

    private String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
