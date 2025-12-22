package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.UserBookingDTO;
import com.elevata.gsrtc.service.UserBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/cancel/{reference}")
    public ResponseEntity<String> cancelBooking(@PathVariable String reference) {
        String response;
        if (reference.length() == 10) {
            response = bookingService.cancelBookingByPnr(reference);
        } else {
            response = bookingService.deletePassengerByPassRef(reference);
        }
        return ResponseEntity.ok(response);
    }
}
