package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.RouteDTO;
import com.elevata.gsrtc.entity.BusDepot;
import com.elevata.gsrtc.entity.BusRoute;
import com.elevata.gsrtc.repository.BusDepotRepository;
import com.elevata.gsrtc.repository.BusRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusRouteService {
    private BusRouteRepository routeRepository;
    private BusDepotRepository busDepotRepository;

    @Autowired
    public BusRouteService(BusRouteRepository routeRepository, BusDepotRepository busDepotRepository) {
        this.routeRepository = routeRepository;
        this.busDepotRepository = busDepotRepository;
    }


    public String addRoute(RouteDTO routeDTO, String duration) {
        Optional<BusDepot> busDepot = busDepotRepository.findById(routeDTO.getDepotId());
        int durationInMinutes = convertToMinutes(duration);

        BusRoute route = new BusRoute();
        route.setRouteName(routeDTO.getRouteName());
        route.setStartPoint(routeDTO.getStartingPoint());
        route.setEndPoint(routeDTO.getEndingPoint());
        route.setClassType(routeDTO.getClassType());
        route.setDistance(routeDTO.getDistance());
        route.setDuration(durationInMinutes);
        busDepot.ifPresent(route::setBusDepot);

        routeRepository.save(route);
        return "Route has been added to DB.";
    }

    private int convertToMinutes(String duration) {
        String[] parts = duration.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours * 60 + minutes;
    }

    public List<RouteDTO> findAll() {
        List<BusRoute> routes = routeRepository.findAll();
        List<RouteDTO> routeDTOS = new ArrayList<>(routes.size());

        routes.forEach(route -> {
            RouteDTO routeDTO = new RouteDTO();
            routeDTO.setRouteName(route.getRouteName());
            routeDTO.setStartingPoint(route.getStartPoint());
            routeDTO.setEndingPoint(route.getEndPoint());
            routeDTO.setDepotId(route.getBusDepot().getDepotId());

            routeDTOS.add(routeDTO);
        });


        return routeDTOS;
    }

    public List<RouteDTO> findByDepotId(int depotId) {
        List<BusRoute> routes = routeRepository.findAllByBusDepotDepotId(depotId);
        List<RouteDTO> routeDTOS = new ArrayList<>(routes.size());

        routes.forEach(route -> {
            RouteDTO routeDTO = new RouteDTO();
            routeDTO.setRouteId(route.getRouteId());
            routeDTO.setRouteName(route.getRouteName());
            routeDTO.setStartingPoint(route.getStartPoint());
            routeDTO.setEndingPoint(route.getEndPoint());
            routeDTO.setClassType(route.getClassType());
            routeDTO.setDistance(route.getDistance());
            routeDTO.setDuration(route.getDuration());
            routeDTO.setDepotId(route.getBusDepot().getDepotId());

            routeDTOS.add(routeDTO);
        });

        return routeDTOS;
    }
}
