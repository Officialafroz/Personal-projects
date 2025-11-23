package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.Booking;
import com.elevata.gsrtc.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public List<Booking> findAll() {
        return bookingService.findAll();
    }

    @GetMapping("/{bookingId}")
    public Booking findById(@PathVariable int bookingId) {
        return bookingService.findById(bookingId);
    }

    @PostMapping("/add")
    public void add(@RequestBody Booking booking) {
        bookingService.save(booking);
    }

    @DeleteMapping("/delete/{BookingId}")
    public void delete(@PathVariable int bookingId) {
        bookingService.delete(bookingId);
    }
}
