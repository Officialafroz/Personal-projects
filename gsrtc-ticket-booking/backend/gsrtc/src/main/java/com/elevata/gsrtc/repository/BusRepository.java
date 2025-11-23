package com.elevata.gsrtc.repository;

import com.elevata.gsrtc.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    Bus findByBusNumber(String busNumber);
    List<Bus> findAllByBusDepotDepotId(Integer depotId);
}
