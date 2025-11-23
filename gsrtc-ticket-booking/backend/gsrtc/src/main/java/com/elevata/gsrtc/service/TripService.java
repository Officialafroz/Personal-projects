package com.elevata.gsrtc.service;

import com.elevata.gsrtc.repository.TripRepository;
import com.elevata.gsrtc.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private TripRepository tripRepository;
    private BusService busService;
    private RouteService routeService;

    @Autowired
    public TripService(TripRepository tripRepository, BusService busService,
                       RouteService routeService) {
        this.tripRepository = tripRepository;
        this.busService = busService;
        this.routeService = routeService;
    }

    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    public Trip findById(int tripId) {
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);

        if (optionalTrip.isPresent()) {
            return optionalTrip.get();
        } else {
            throw new RuntimeException("Trip not found for id: " + tripId);
        }
    }

    public void save(Trip trip) {
        int busId = trip.getBus().getBusId();
        int routeId = trip.getRoute().getRouteId();

        if (trip.getBus() != null && trip.getRoute() != null && busId > 0 && routeId > 0) {
            Bus existingBus = busService.findById(busId);
            BusRoute existingRoute = routeService.findById(routeId);

            if (existingBus == null || existingRoute == null) {
                throw new RuntimeException(
                        String.format("Invalid bus id - %d or route id - %d", busId, routeId));
            }
            trip.setBus(existingBus);
            trip.setRoute(existingRoute);
        }

        tripRepository.save(trip);
        System.out.println("Trip is successfully created.");
    }

    public void delete(int tripId) {
        tripRepository.deleteById(tripId);
        System.out.println("Trip is safely deleted.");
    }
}
