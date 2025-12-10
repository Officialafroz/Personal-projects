package com.elevata.gsrtc.service;

import com.elevata.gsrtc.entity.Booking;
import com.elevata.gsrtc.entity.Payment;
import com.elevata.gsrtc.repository.BookingRepository;
import com.elevata.gsrtc.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingPaymentService {
    private PaymentRepository paymentRepository;
    private BookingRepository bookingRepository;

    @Autowired
    public BookingPaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }


    public String save(double totalAmount, String pnr) {
        Booking existingBooking = bookingRepository.findByPnr(pnr);
        Payment payment = new Payment();
        payment.setAmount(totalAmount);
        payment.setPaymentMethod("UPI");
        payment.setStatus("PAID");
        payment.setTransactionRef(generateTransRef());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setBooking(existingBooking);

        paymentRepository.save(payment);
        System.out.println("Payment saved successfully for " + existingBooking.getUser());

        return "Payment saved successfully.";
    }

    private static String generateTransRef() {
        return "TXN" + System.currentTimeMillis();
    }
}
