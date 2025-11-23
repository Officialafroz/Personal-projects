package com.elevata.gsrtc.service;

import com.elevata.gsrtc.repository.RouteStopsRepository;
import com.elevata.gsrtc.entity.BusRoute;
import com.elevata.gsrtc.entity.RouteStops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteStopsService {
    private RouteStopsRepository stopsRepository;
    private RouteService routeService;

    @Autowired
    public RouteStopsService(RouteStopsRepository stopsRepository, RouteService routeService) {
        this.stopsRepository = stopsRepository;
        this.routeService = routeService;
    }

    public List<RouteStops> findAll() {
        return stopsRepository.findAll();
    }

    public RouteStops findById(int stopId) {
        Optional<RouteStops> stopsOptional = stopsRepository.findById(stopId);

        if (stopsOptional.isEmpty()) {
            throw new RuntimeException("Route stop not found for id: " + stopId);
        }

        return stopsOptional.get();
    }

    public void save(RouteStops stops) {
        if (stops.getBusRoute() != null && stops.getBusRoute().getRouteId() > 0) {
            int routeId = stops.getBusRoute().getRouteId();
            BusRoute existingRoute = routeService.findById(routeId);

            if (existingRoute == null) {
                throw new RuntimeException(
                        String.format("Invalid route id - %d", routeId));
            }
            stops.setBusRoute(existingRoute);
        }

        stopsRepository.save(stops);
        System.out.println("Route stops is successfully created.");
    }

    public void delete(int stopId) {
        stopsRepository.deleteById(stopId);
        System.out.println("Route stop is safely deleted.");
    }
}
