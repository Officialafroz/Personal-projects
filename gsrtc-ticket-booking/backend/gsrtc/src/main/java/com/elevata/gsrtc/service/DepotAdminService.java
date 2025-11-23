package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.DepotAdminDTO;
import com.elevata.gsrtc.entity.Bus;
import com.elevata.gsrtc.entity.BusDepot;
import com.elevata.gsrtc.repository.BusDepotRepository;
import com.elevata.gsrtc.repository.DepotAdminRepository;
import com.elevata.gsrtc.entity.DepotAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepotAdminService {
    private DepotAdminRepository depotAdminRepository;
    private BusDepotRepository busDepotRepository;

    @Autowired
    public DepotAdminService(DepotAdminRepository depotAdminRepository,
                             BusDepotRepository busDepotRepository) {
        this.depotAdminRepository = depotAdminRepository;
        this.busDepotRepository = busDepotRepository;
    }

    public List<DepotAdmin> getDepotAdminList() {
        List<DepotAdmin> depotAdmins = depotAdminRepository.findAll();

        if (depotAdmins == null) {
            throw new RuntimeException("There are no users found in database.");
        }
        return depotAdmins;
    }

    public DepotAdmin findById(int adminId) {
        Optional<DepotAdmin> depotAdmin = depotAdminRepository.findById(adminId);

        if (depotAdmin.isPresent()) {
            return depotAdmin.get();
        } else {
            throw new RuntimeException("Depot admin not found for id: " + adminId);
        }
    }

    public void save(DepotAdmin depotAdmin) {
        if (depotAdmin.getBusDepot() != null && depotAdmin.getBusDepot().getDepotId() > 0) {
            int depotId = depotAdmin.getBusDepot().getDepotId();
            Optional<BusDepot> existingDepot = busDepotRepository.findById(depotId);

            existingDepot.ifPresent(depotAdmin::setBusDepot);

            depotAdminRepository.save(depotAdmin);
            System.out.println("Depot admin saved.");
        } else {
            System.out.println("Depot admin or depot id is invalid.");
        }
    }

    public void deleteById(int depotId) {
        depotAdminRepository.deleteById(depotId);
        System.out.println("Bus depot is removed.");
    }

    public DepotAdminDTO findByEmail(String email) {
        DepotAdmin existingDepotAdmin = depotAdminRepository.findDepotAdminByEmail(email);
        DepotAdminDTO adminDTO = new DepotAdminDTO();
        adminDTO.setName(existingDepotAdmin.getName());
        adminDTO.setDepotId(existingDepotAdmin.getBusDepot().getDepotId());

        return adminDTO;
    }
}
