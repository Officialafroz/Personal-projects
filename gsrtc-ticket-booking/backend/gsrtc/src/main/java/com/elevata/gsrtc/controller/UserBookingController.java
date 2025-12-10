package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.UserBookingDTO;
import com.elevata.gsrtc.service.UserBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userBooking")
public class UserBookingController {
    private UserBookingService bookingService;

    @Autowired
    public UserBookingController(UserBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public String add(@RequestBody UserBookingDTO bookingDTO) {
        return bookingService.save(bookingDTO);
    }
}
