package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.PassengerDTO;
import com.elevata.gsrtc.entity.*;
import com.elevata.gsrtc.repository.BookingRepository;
import com.elevata.gsrtc.repository.PassengerRepository;
import com.elevata.gsrtc.repository.RouteStopsRepository;
import com.elevata.gsrtc.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingPassengerService {
    private PassengerRepository passengerRepository;
    private TripRepository tripRepository;
    private BookingRepository bookingRepository;
    private RouteStopsRepository stopsRepository;
    private FareService fareService;

    @Autowired
    public BookingPassengerService(PassengerRepository passengerRepository, TripRepository tripRepository, BookingRepository bookingRepository, RouteStopsRepository stopsRepository, FareService fareService) {
        this.passengerRepository = passengerRepository;
        this.tripRepository = tripRepository;
        this.bookingRepository = bookingRepository;
        this.stopsRepository = stopsRepository;
        this.fareService = fareService;
    }

    public String save(List<PassengerDTO> passengers, String tripCode, String pnr) {
        Booking existingBooking = bookingRepository.findByPnr(pnr);
        System.out.println(passengers);

        passengers.forEach(passenger -> {
            Passenger psg = new Passenger(
                    existingBooking,
                    passenger.getFullName(),
                    passenger.getAge(),
                    passenger.getGender(),
                    passenger.getSeat(),
                    passenger.getBoardingPoint(),
                    passenger.getDestination(),
                    passenger.getFare()
            );

            System.out.println(passenger.getFullName() + " : " + passenger.getSeat());

            passengerRepository.save(psg);
            System.out.println("Passenger " + psg.getName() + " added successfully");
        });

        return "Passenger added.";
    }

    public double getFare(String boardingPoint, String destination, String tripCode) {

        // 1. Get route from trip
        System.out.println(tripCode);
        BusRoute route = tripRepository.findByTripCode(tripCode).getRoute();
        System.out.println(route);
        List<RouteStops> stopsList = stopsRepository.findByBusRouteRouteId(route.getRouteId());
        System.out.println(stopsList);

        // 2. Variables to store the matched stops
        RouteStops sourceStop = null;
        RouteStops destinationStop = null;

        // 3. Loop and find matching stops
        for (RouteStops stop : stopsList) {
            if (stop.getStopName().equalsIgnoreCase(boardingPoint)) {
                sourceStop = stop;
            }
            if (stop.getStopName().equalsIgnoreCase(destination)) {
                destinationStop = stop;
            }
        }

        // 4. Validate
        if (sourceStop == null || destinationStop == null) {
            throw new RuntimeException("Invalid boarding or destination stop.");
        }

        if (sourceStop.getStopOrder() >= destinationStop.getStopOrder()) {
            System.out.println(sourceStop + "\n" + destinationStop);
            throw new RuntimeException("Boarding point must be before destination.");
        }

        // 5. Calculate fare correctly
        return fareService.calcSeatRate(sourceStop, destinationStop);
    }
}
