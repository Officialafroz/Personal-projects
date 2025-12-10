package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.UserBookingDTO;
import com.elevata.gsrtc.entity.Booking;
import com.elevata.gsrtc.entity.Trip;
import com.elevata.gsrtc.entity.User;
import com.elevata.gsrtc.repository.BookingRepository;
import com.elevata.gsrtc.repository.TripRepository;
import com.elevata.gsrtc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserBookingService {
    private BookingRepository bookingRepository;
    private TripRepository tripRepository;
    private UserRepository userRepository;

    @Autowired
    public UserBookingService(BookingRepository bookingRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    public String save(UserBookingDTO bookingDTO) {
        User user = userRepository.findUserByEmail(bookingDTO.getEmail());
        Trip trip = tripRepository.findByTripCode(bookingDTO.getTripCode());
        String pnr = generatePNR();

        Booking newBooking = new Booking();
        newBooking.setUser(user);
        newBooking.setBookingTime(LocalDateTime.now());
        newBooking.setTrip(trip);
        newBooking.setPnr(pnr);
        newBooking.setJourneyDate(bookingDTO.getJourneyDate());
        newBooking.setTotalFare(bookingDTO.getTotalFare());

        bookingRepository.save(newBooking);
        System.out.println("Booking saved successfully for " + user.getName() + ".");
        return pnr;
    }

    public static String generatePNR() {
        String prefix = "GS";

        // last 6 digits of timestamp
        String timePart = String.valueOf(System.currentTimeMillis());
        timePart = timePart.substring(timePart.length() - 6);

        // 2-digit random number (10–99)
        int random = 10 + (int)(Math.random() * 90);

        return prefix + timePart + random;
    }


}
