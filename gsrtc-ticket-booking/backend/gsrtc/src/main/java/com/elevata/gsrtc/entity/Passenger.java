package com.elevata.gsrtc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private int passengerId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "seat_no")
    private int seatNo;

    @Column(name = "boarding_point")
    private String boardingPoint;

    @Column(name = "destination_point")
    private String destinationPoint;

    @Column(name = "individual_fare")
    private double individualFare = 0;

    public Passenger(String name, int age, String gender, int seatNo,
                     String boardingPoint, String destinationPoint) {
        this.name = name;
        this.age = age;
        if (gender.equals("male") || gender.equals("female")) {
            this.gender = gender;
        } else {
            throw new RuntimeException("Invalid Gender : " + gender);
        }
        this.seatNo = seatNo;
        this.boardingPoint = boardingPoint;
        this.destinationPoint = destinationPoint;
    }
}
