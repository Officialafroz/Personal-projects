package com.elevata.gsrtc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "bus_depot")
public class BusDepot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depot_id")
    private int depotId;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    public BusDepot(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
