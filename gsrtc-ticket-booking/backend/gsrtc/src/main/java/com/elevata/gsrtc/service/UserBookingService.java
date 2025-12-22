package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.UserBookingDTO;
import com.elevata.gsrtc.entity.Booking;
import com.elevata.gsrtc.entity.Passenger;
import com.elevata.gsrtc.entity.Trip;
import com.elevata.gsrtc.entity.User;
import com.elevata.gsrtc.repository.BookingRepository;
import com.elevata.gsrtc.repository.PassengerRepository;
import com.elevata.gsrtc.repository.TripRepository;
import com.elevata.gsrtc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserBookingService {
    private BookingRepository bookingRepository;
    private PassengerRepository passengerRepository;
    private TripRepository tripRepository;
    private UserRepository userRepository;

    @Autowired
    public UserBookingService(BookingRepository bookingRepository, PassengerRepository passengerRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
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

    public String cancelBookingByPnr(String pnr) {
        Booking booking = bookingRepository.findByPnr(pnr);

        if (booking != null) {
            List<Passenger> passengers = passengerRepository.findAllByBookingBookingId(booking.getBookingId());

            booking.setStatus("CANCELLED");
            passengers.forEach(passenger -> passenger.setStatus("CANCELLED"));

            passengerRepository.saveAll(passengers);
            bookingRepository.save(booking);
            return "Booking cancelled successfully for PNR: " + pnr;
        } else {
            return "Booking not found for PNR: " + pnr;
        }
    }

    public String deletePassengerByPassRef(String passRef) {
        Optional<Passenger> passengerOpt = passengerRepository.findByPassRef(passRef);

        if (passengerOpt.isPresent()) {
            Passenger passenger = passengerOpt.get();
            passenger.setStatus("CANCELLED");

            passengerRepository.save(passenger);
            return "Passenger booking cancelled successfully with reference: " + passRef;
        } else {
            return "Passenger not found with reference: " + passRef;
        }
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
