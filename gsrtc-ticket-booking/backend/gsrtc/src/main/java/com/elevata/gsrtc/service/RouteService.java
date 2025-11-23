package com.elevata.gsrtc.service;

import com.elevata.gsrtc.repository.BusRouteRepository;
import com.elevata.gsrtc.entity.BusRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    private BusRouteRepository routeRepository;

    @Autowired
    public RouteService(BusRouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<BusRoute> findAll() {
        return routeRepository.findAll();
    }

    public BusRoute findById(int routeId) {
        Optional<BusRoute> optionalRoute = routeRepository.findById(routeId);

        if (optionalRoute.isPresent()) {
            return optionalRoute.get();
        } else {
            throw new RuntimeException("Booking not found for id: " + routeId);
        }
    }

    public void save(BusRoute busRoute) {
        routeRepository.save(busRoute);
        System.out.println("Bus route is successfully created.");
    }

    public void delete(int routeId) {
        routeRepository.deleteById(routeId);
        System.out.println("Route is safely deleted.");
    }
}
