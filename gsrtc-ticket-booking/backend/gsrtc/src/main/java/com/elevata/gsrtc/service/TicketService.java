package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.TicketDTO;
import com.elevata.gsrtc.entity.Booking;
import com.elevata.gsrtc.entity.Passenger;
import com.elevata.gsrtc.entity.Trip;
import com.elevata.gsrtc.repository.BookingRepository;
import com.elevata.gsrtc.repository.PassengerRepository;
import com.elevata.gsrtc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private PassengerRepository passengerRepository;
    private UserRepository userRepository;
    private BookingRepository bookingRepository;

    @Autowired
    public TicketService(PassengerRepository passengerRepository, UserRepository userRepository, BookingRepository bookingRepository) {
        this.passengerRepository = passengerRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<TicketDTO> getTickets(String email) {
        List<TicketDTO> tickets = null;
        int userId = userRepository.findUserByEmail(email).getUid();
        List<Booking> userBooking = bookingRepository.findAllByUserUid(userId);
        System.out.println(userBooking);

        if (userBooking != null){
            tickets = new ArrayList<>();
            for (Booking booking : userBooking) {
                TicketDTO ticketDTO = new TicketDTO();
                ticketDTO.setTripCode(booking.getTrip().getTripCode());
                ticketDTO.setRoute(booking.getTrip().getRoute().getRouteName());
                ticketDTO.setJourneyDate(LocalDate.parse(booking.getJourneyDate()));
                ticketDTO.setClassType(booking.getTrip().getRoute().getClassType());

                List<Passenger> passengers = passengerRepository.findAllByBookingBookingId(booking.getBookingId());
                List<String> passengerNames = passengers.stream()
                        .map(Passenger::getName)
                        .toList();
                ticketDTO.setPassengers(passengerNames);

                Set<Integer> seats = passengers.stream()
                        .map(Passenger::getSeatNo)
                        .collect(Collectors.toSet());
                ticketDTO.setSeats(seats);

                double totalFare = passengers.stream()
                        .mapToDouble(Passenger::getIndividualFare)
                        .sum();

                ticketDTO.setTotalFare(totalFare);

                tickets.add(ticketDTO);
                System.out.println(ticketDTO);
            }
        }

        return tickets;
    }

    public TicketDTO getTicket(String pnr) {
        Booking existingBooking = bookingRepository.findByPnr(pnr);
        if (!existingBooking.getStatus().equalsIgnoreCase("cancelled")) {
            List<Passenger> passengers = passengerRepository
                    .findAllByBookingBookingId(existingBooking.getBookingId());
            Trip existingTrip = existingBooking.getTrip();
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setTripCode(existingTrip.getTripCode());
            ticketDTO.setRoute(existingTrip.getRoute().getRouteName());
            ticketDTO.setJourneyDate(LocalDate.parse(existingBooking.getJourneyDate()));
            ticketDTO.setClassType(existingTrip.getRoute().getClassType());

            List<String> passengerNames = passengers.stream()
                    .map(Passenger::getName).toList();
            ticketDTO.setPassengers(passengerNames);

            Set<Integer> seats = passengers.stream()
                    .map(Passenger::getSeatNo).collect(Collectors.toSet());
            ticketDTO.setSeats(seats);

            double totalFare = passengers.stream()
                    .mapToDouble(Passenger::getIndividualFare)
                    .sum();
            ticketDTO.setTotalFare(totalFare);
            return ticketDTO;
        }
        return null;
    }
}
