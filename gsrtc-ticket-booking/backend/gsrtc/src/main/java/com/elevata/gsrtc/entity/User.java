package com.elevata.gsrtc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
//@ToString(exclude = "otpDetailsList")
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private int uid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private String role = "END USER";

//    @OneToMany(mappedBy = "user",
//               cascade = {CascadeType.MERGE, CascadeType.PERSIST,
//                          CascadeType.DETACH, CascadeType.REFRESH})
//    private List<OtpDetails> otpDetailsList;

    public User(String name, String email, String phoneNumber, String gender) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

//    public void add(OtpDetails otpDetails) {
//        if (otpDetailsList == null) {
//            otpDetailsList = new ArrayList<>();
//        }
//
//        otpDetailsList.add(otpDetails);
//        otpDetails.setUser(this);
//    }
}
