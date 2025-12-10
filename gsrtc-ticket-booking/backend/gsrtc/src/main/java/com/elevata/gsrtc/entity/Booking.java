package com.elevata.gsrtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Trip trip;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Column(name = "journey_date")
    private String journeyDate = LocalDateTime.now().toString();

    @Column(name = "pnr")
    private String pnr;

    @Column(name = "total_fare")
    private double totalFare;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

    @Column(name = "status")
    private String status;

//    @OneToMany(mappedBy = "booking",
//               fetch = FetchType.LAZY,
//               cascade = CascadeType.ALL)
//    private List<Passenger> totalPassengers;
}
