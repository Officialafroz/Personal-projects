package com.elevata.gsrtc.service;

import com.elevata.gsrtc.repository.BookingRepository;
import com.elevata.gsrtc.entity.Booking;
import com.elevata.gsrtc.entity.Trip;
import com.elevata.gsrtc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private UserService userService;
    private TripService tripService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserService userService,
                          TripService tripService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.tripService = tripService;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking findById(int bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        } else {
            throw new RuntimeException("Booking not found for id: " + bookingId);
        }
    }

    public void save(Booking booking) {
        if (booking.getUser() != null && booking.getTrip() != null &&
                booking.getUser().getUid() > 0 && booking.getTrip().getTripId() > 0
        ) {
            int userId = booking.getUser().getUid();
            int tripId = booking.getTrip().getTripId();


            User existingUser = userService.findById(userId);
            Trip existingTrip = tripService.findById(tripId);

            if (existingUser == null || existingTrip == null) {
                throw new RuntimeException(
                        String.format("Invalid user id - %d or bus id - %d", userId, tripId));
            }
            booking.setUser(existingUser);
            booking.setTrip(existingTrip);
        }

        bookingRepository.save(booking);
        System.out.println("Booking is successfully created.");
    }

    public void delete(int bookingId) {
        bookingRepository.deleteById(bookingId);
        System.out.println("Booking is safely deleted.");
    }
}
