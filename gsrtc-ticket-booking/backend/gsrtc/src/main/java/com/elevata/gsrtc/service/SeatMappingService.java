package com.elevata.gsrtc.service;

import com.elevata.gsrtc.entity.Booking;
import com.elevata.gsrtc.entity.Passenger;
import com.elevata.gsrtc.repository.BookingRepository;
import com.elevata.gsrtc.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SeatMappingService {
    private PassengerRepository passengerRepository;
    private BookingRepository bookingRepository;

    @Autowired
    public SeatMappingService(PassengerRepository passengerRepository, BookingRepository bookingRepository) {
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
    }


    public Set<Integer> mapSeats(String tripCode) {
        List<Booking> bookings = bookingRepository.findAllByTripTripCode(tripCode);
        Set<Integer> seats = new HashSet<>();

        if (bookings != null) {
            for (Booking booking : bookings) {
                List<Passenger> passengers = passengerRepository
                        .findAllByBookingBookingId(booking.getBookingId());

                for (Passenger passenger : passengers) {
                    seats.add(passenger.getSeatNo());  // Set ignores duplicates automatically
                }
            }
        }

        System.out.println(bookings);
        System.out.println(seats);

        return seats;
    }
}
