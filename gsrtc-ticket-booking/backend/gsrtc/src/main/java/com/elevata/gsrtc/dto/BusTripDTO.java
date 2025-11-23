package com.elevata.gsrtc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusTripDTO {
    private int routeId;
    private String busNumber;
    private LocalDate date;
    private LocalDateTime departureTime;
    private LocalDateTime destinationTime;
    private String status;
    private int depotId;
}
