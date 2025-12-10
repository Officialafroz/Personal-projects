package com.elevata.gsrtc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBookingDTO {
    private String email;
    private String tripCode;
    private String journeyDate;
    private double totalFare;
}
