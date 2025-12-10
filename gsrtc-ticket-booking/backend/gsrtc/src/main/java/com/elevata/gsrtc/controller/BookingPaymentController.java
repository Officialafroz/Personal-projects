package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.service.BookingPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookingPayment")
public class BookingPaymentController {
    private BookingPaymentService paymentService;

    @Autowired
    public BookingPaymentController(BookingPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public String add(@RequestParam double totalAmount, @RequestParam String pnr) {
        return paymentService.save(totalAmount, pnr);
    }
}
