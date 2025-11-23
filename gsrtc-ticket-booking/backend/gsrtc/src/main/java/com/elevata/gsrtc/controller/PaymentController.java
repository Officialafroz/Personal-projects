package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.Payment;
import com.elevata.gsrtc.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public List<Payment> getPaymentList() {
        return paymentService.findAll();
    }

    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable int paymentId) {
        return paymentService.findById(paymentId);
    }

    @PostMapping("/add")
    public void add(@RequestBody Payment payment) {
        paymentService.save(payment);
    }

    @DeleteMapping("/delete/{paymentId}")
    public void delete(@PathVariable int paymentId) {
        paymentService.delete(paymentId);
    }
}
