package com.elevata.gsrtc.service;

import com.elevata.gsrtc.repository.PaymentRepository;
import com.elevata.gsrtc.entity.Booking;
import com.elevata.gsrtc.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private BookingService bookingService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, BookingService bookingService) {
        this.paymentRepository = paymentRepository;
        this.bookingService = bookingService;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(int paymentId) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);

        if (paymentOptional.isEmpty()) {
            throw new RuntimeException("Payment not found for id : " + paymentId);
        }

        return paymentOptional.get();
    }

    public void save(Payment payment) {
        if (payment.getBooking() != null && payment.getBooking().getBookingId() > 0) {
            int bookingId = payment.getBooking().getBookingId();

            Booking existingBooking = bookingService.findById(bookingId);
            if (existingBooking == null) {
                throw new RuntimeException("Invalid booking id - " + bookingId);
            }

            // Attach the managed booking entity
            payment.setBooking(existingBooking);
        } else {
            throw new RuntimeException("Booking information missing or invalid.");
        }

        // Now save payment
        paymentRepository.save(payment);
        System.out.println("Payment saved.");
    }


    public void delete(int paymentId) {
        paymentRepository.deleteById(paymentId);
        System.out.println("Payment safely deleted.");
    }
}
