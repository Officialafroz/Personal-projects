package com.elevata.gsrtc.repository;

import com.elevata.gsrtc.entity.BusDepot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusDepotRepository extends JpaRepository<BusDepot, Integer> {
}
