package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.SearchResultDTO;
import com.elevata.gsrtc.entity.BusRoute;
import com.elevata.gsrtc.entity.RouteStops;
import com.elevata.gsrtc.entity.Trip;
import com.elevata.gsrtc.repository.BusRouteRepository;
import com.elevata.gsrtc.repository.RouteStopsRepository;
import com.elevata.gsrtc.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchBusService {
    private TripRepository tripRepository;
    private RouteStopsRepository stopsRepository;
    private BusRouteRepository busRouteRepository;
    private FareService fareService;

    @Autowired
    public SearchBusService(TripRepository tripRepository, RouteStopsRepository stopsRepository, BusRouteRepository busRouteRepository, FareService fareService) {
        this.tripRepository = tripRepository;
        this.stopsRepository = stopsRepository;
        this.busRouteRepository = busRouteRepository;
        this.fareService = fareService;
    }

    public List<SearchResultDTO> getSearchResult(
            LocalDate journeyDate,
            String source,
            String destination
    ) {
        List<Trip> dateTrips = tripRepository.findAllByTripDate(journeyDate);
        if (dateTrips.isEmpty()) {
            return new ArrayList<>();
        }

        List<SearchResultDTO> tripDTOs = new ArrayList<>();

        // Pre-load stops for source and destination
        List<RouteStops> sourceStops = stopsRepository.findAllByStopName(source);
        List<RouteStops> destinationStops = stopsRepository.findAllByStopName(destination);

        for (Trip trip : dateTrips) {
            BusRoute route = trip.getRoute();
            List<RouteStops> stops = stopsRepository.findByBusRouteRouteId(route.getRouteId());
            stops.removeFirst();
            stops.removeLast();

            // Find source stop ON THIS route
            RouteStops sourceStop = sourceStops.stream()
                    .filter(s -> s.getBusRoute().getRouteId() == route.getRouteId())
                    .findFirst()
                    .orElse(null);

            // Find destination stop ON THIS route
            RouteStops destinationStop = destinationStops.stream()
                    .filter(s -> s.getBusRoute().getRouteId() == route.getRouteId())
                    .findFirst()
                    .orElse(null);

            // Skip if either stop missing or order wrong
            if (sourceStop == null || destinationStop == null) continue;
            if (sourceStop.getStopOrder() >= destinationStop.getStopOrder()) continue;

            double fare = fareService.calcSeatRate(sourceStop, destinationStop);

            SearchResultDTO dto = new SearchResultDTO();
            dto.setRouteName(route.getRouteName());
            dto.setTripCode(trip.getTripCode());
            dto.setClassType(trip.getBus().getBusType());
            dto.setStops(stops);
            System.out.println(route.getRouteName() + " : " + stops);
            dto.setDepartureTime(extractTime(trip.getDepartureTime()));
            dto.setDestinationTime(extractTime(trip.getDestinationTime()));
            dto.setSeatRate(fare);
            dto.setVacantSeats(trip.getBus().getBusLayout().getTotalSeats());

            tripDTOs.add(dto);
        }

        return tripDTOs;
    }

    public String extractTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter);
    }

}
