package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.BusTripDTO;
import com.elevata.gsrtc.dto.TripDTO;
import com.elevata.gsrtc.entity.Bus;
import com.elevata.gsrtc.entity.BusDepot;
import com.elevata.gsrtc.entity.BusRoute;
import com.elevata.gsrtc.entity.Trip;
import com.elevata.gsrtc.repository.BusDepotRepository;
import com.elevata.gsrtc.repository.BusRepository;
import com.elevata.gsrtc.repository.BusRouteRepository;
import com.elevata.gsrtc.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusTripService {
    private TripRepository tripRepository;
    private BusRepository busRepository;
    private BusRouteRepository routeRepository;
    private BusDepotRepository busDepotRepository;

    @Autowired
    public BusTripService(TripRepository tripRepository, BusRepository busRepository, BusRouteRepository routeRepository, BusDepotRepository busDepotRepository) {
        this.tripRepository = tripRepository;
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.busDepotRepository = busDepotRepository;
    }

    public void save(BusTripDTO busTripDTO) {
        Bus bus = busRepository.findByBusNumber(busTripDTO.getBusNumber());
        Optional<BusRoute> route = routeRepository.findById(busTripDTO.getRouteId());
        Optional<BusDepot> busDepot = busDepotRepository.findById(busTripDTO.getDepotId());

        Trip trip = new Trip();

        String startingPoint = route.map(BusRoute::getStartPoint).orElse(null);
        String endingPoint = route.map(BusRoute::getEndPoint).orElse(null);

        if (route.isPresent() || busDepot.isPresent()) {
            BusRoute busRoute = route.get();
            startingPoint = busRoute.getStartPoint();
            endingPoint = busRoute.getEndPoint();
            trip.setRoute(busRoute);
            trip.setBusDepot(busDepot.get());
        }


        String tripCode = generateTripCode(
                busTripDTO.getDepartureTime().toString(),
                startingPoint,
                endingPoint,
                bus.getBusType(),
                bus.getBusDepot().getDepotId()
        );

        System.out.println(tripCode);

        trip.setTripCode(tripCode);
        trip.setTripDate(busTripDTO.getDate());
        trip.setDepartureTime(busTripDTO.getDepartureTime());
        trip.setDestinationTime(busTripDTO.getDestinationTime());
        trip.setStatus(busTripDTO.getStatus());
        trip.setBus(bus);

        tripRepository.save(trip);
    }

    public String generateTripCode(String departureTime, String from, String to,
                                   String busClass, int depotNumber) {
        // Extract only time if full datetime is passed (e.g., 2025-11-10T05:00)
        String timePart = departureTime.contains("T")
                ? departureTime.split("T")[1].replace(":", "")
                : departureTime.replace(":", "");

        if (timePart.length() < 4) {
            timePart = String.format("%04d", Integer.parseInt(timePart));
        }

        String fromPart = getCondensedName(from);
        String toPart = getCondensedName(to);
        String classPart = busClass.length() >= 2
                ? busClass.substring(0, 2).toUpperCase()
                : busClass.toUpperCase();
        String depotPart = String.format("%02d", depotNumber);

        return (timePart + fromPart + toPart + classPart + depotPart).toUpperCase();
    }


    private String getCondensedName(String name) {
        int length = 3;

        if (name == null || name.isBlank()) return "XXX";
        String cleaned = name.replaceAll("[^A-Za-z]", "").toUpperCase();

        return cleaned.length() >= length ? cleaned.substring(0, length) : String.format("%-" + length + "s", cleaned).replace(' ', 'X');
    }

    public List<TripDTO> findAllByDepotId(int depotId) {
        List<Trip> trips = tripRepository.findAllByBusDepotDepotId(depotId);
        System.out.println(trips);
        List<TripDTO> tripDTOS = new ArrayList<>(trips.size());

        for (Trip trip : trips) {
            TripDTO tripDTO = new TripDTO();

            tripDTO.setRouteName(trip.getRoute().getRouteName());
            tripDTO.setBusNumber(trip.getBus().getBusNumber());
            tripDTO.setDate(trip.getTripDate());
            tripDTO.setStatus(trip.getStatus());
            tripDTO.setDepartureTime(trip.getDepartureTime());
            tripDTO.setDestinationTime(trip.getDestinationTime());
            System.out.println(tripDTO);

            tripDTOS.add(tripDTO);
        }

        return tripDTOS;
    }
}
