package com.elevata.gsrtc.service;

import com.elevata.gsrtc.repository.PassengerRepository;
import com.elevata.gsrtc.entity.Booking;
import com.elevata.gsrtc.entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private PassengerRepository passengerRepository;
    private BookingService bookingService;
    private ReferenceNumberService numberService;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository, BookingService bookingService, ReferenceNumberService numberService) {
        this.passengerRepository = passengerRepository;
        this.bookingService = bookingService;
        this.numberService = numberService;
    }

    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    public List<Passenger> findByBookingId(int bookingId) {
        return passengerRepository.findAllByBookingBookingId(bookingId);
    }

    public Passenger findById(int passengerId) {
        Optional<Passenger> passengerOptional = passengerRepository.findById(passengerId);

        if (passengerOptional.isEmpty()) {
            throw new RuntimeException("Passenger not found for id : " + passengerId);
        }

        return passengerOptional.get();
    }

    public void save(Passenger passenger) {
        if (passenger.getBooking() != null && passenger.getBooking().getBookingId() > 0) {
            int bookingId = passenger.getBooking().getBookingId();
            Booking existingBooking = bookingService.findById(bookingId);

            if (existingBooking == null) {
                throw new RuntimeException(
                        String.format("Invalid booking id - %d", bookingId));
            }
            passenger.setBooking(existingBooking);
        }
        passengerRepository.save(passenger);
        System.out.println("Refunds is successfully created.");
    }

    public void delete(int passengerId) {
        passengerRepository.deleteById(passengerId);
        System.out.println("Passenger is deleted safely.");
    }
}
