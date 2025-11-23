package com.elevata.gsrtc.enums;

public enum BookingStatus {
    CONFIRMED("confirmed"),
    CANCELLED("cancelled"),
    IN_PROGRESS("in progress");

    private String status;

    BookingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
