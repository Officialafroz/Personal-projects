package com.elevata.gsrtc.repository;

import com.elevata.gsrtc.entity.DepotAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepotAdminRepository extends JpaRepository<DepotAdmin, Integer> {
    DepotAdmin findDepotAdminByEmail(String email);
    DepotAdmin findByBusDepotDepotId(int depotId);
}
