package com.elevata.gsrtc.repository;

import com.elevata.gsrtc.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
    List<BusRoute> findAllByBusDepotDepotId(int depotId);
    BusRoute findById(int routeId);
}
