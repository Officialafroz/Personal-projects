package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.RouteStopDTO;
import com.elevata.gsrtc.entity.BusRoute;
import com.elevata.gsrtc.entity.RouteStops;
import com.elevata.gsrtc.repository.BusRouteRepository;
import com.elevata.gsrtc.repository.RouteStopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusRouteStopsService {
    private RouteStopsRepository routeStopsRepository;
    private BusRouteRepository busRouteRepository;

    @Autowired
    public BusRouteStopsService(RouteStopsRepository routeStopsRepository, BusRouteRepository busRouteRepository) {
        this.routeStopsRepository = routeStopsRepository;
        this.busRouteRepository = busRouteRepository;
    }


    public List<RouteStopDTO> findRouteStops(int routeId) {
        List<RouteStops> stops = routeStopsRepository.findByBusRouteRouteId(routeId);
        List<RouteStopDTO> stopDTOS = new ArrayList<>(stops.size());

        stops.forEach(stop -> {
            RouteStopDTO stopDTO = new RouteStopDTO();
            stopDTO.setStopName(stop.getStopName());
            stopDTO.setStopOrder(stop.getStopOrder());
            stopDTO.setDistanceFromStart(stop.getDistanceFromStart());
            stopDTO.setDuration(stop.getDuration());
            stopDTO.setFare(stop.getFare());

            stopDTOS.add(stopDTO);
        });

//        System.out.println(stopDTOS);

        return stopDTOS;
    }

    public String save(RouteStopDTO stopDTO) {
        BusRoute route = busRouteRepository.findById(stopDTO.getRouteId());

        RouteStops stop = new RouteStops();
        System.out.println("Route dur: " + route.getDuration());
        System.out.println("Route dis: " + route.getDistance());
        System.out.println("Stop dur: " + stop.getDistanceFromStart());
        int duration = generateDuration(route.getDuration(),
                route.getDistance(), stopDTO.getDistanceFromStart());
        System.out.println(duration);

        stop.setStopName(stopDTO.getStopName());
        stop.setStopOrder(stopDTO.getStopOrder());
        stop.setDistanceFromStart(stopDTO.getDistanceFromStart());
        stop.setDuration(duration);
        stop.setFare(stopDTO.getFare());
        stop.setBusRoute(route);

        routeStopsRepository.save(stop);
        return "Stop saved.";
    }

    public void delete(String stopName) {
        RouteStops stop = routeStopsRepository.findByStopName(stopName);
        routeStopsRepository.delete(stop);
    }

    public int generateDuration(int routeDuration, double routeDistance, double stopDistance) {
        return (int) ((routeDuration/routeDistance) * stopDistance);
    }
}
