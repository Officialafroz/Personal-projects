package com.elevata.gsrtc.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDTO {
    private String busNumber;
    private String busType;
    private int totalSeats;
    private int depotId;
}
