package com.elevata.gsrtc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "edu_trip_destination")
public class EducationalTripDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Integer destinationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private EducationalTripBooking tripBooking;

    @Column(name = "destination_name", length = 150, nullable = false)
    private String destinationName;

    @Column(name = "sequence_no", nullable = false)
    private Integer sequenceNo;
}
