package com.elevata.gsrtc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "depot_admin")
public class DepotAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depot_admin_id")
    private int depotAdminId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private String role = "DEPOT ADMIN";

    @Column(name = "logged_in")
    private boolean loggedIn;

    @OneToOne(cascade = {CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "depot_id")
    private BusDepot busDepot;

    public DepotAdmin(String name, String email,
                      String password, String phoneNumber, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
}
