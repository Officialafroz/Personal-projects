package com.elevata.gsrtc.repository;

import com.elevata.gsrtc.entity.BusRoute;
import com.elevata.gsrtc.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findAllByBusDepotDepotId(int depotId);
    List<Trip> findAllByTripDate(LocalDate tripDate);
    Trip findByTripCode(String tripCode);
}
