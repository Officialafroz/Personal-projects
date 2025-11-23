package com.elevata.gsrtc.service;

import com.elevata.gsrtc.repository.BusDepotRepository;
import com.elevata.gsrtc.entity.BusDepot;
import com.elevata.gsrtc.entity.DepotAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusDepotService {
    private BusDepotRepository busDepotRepository;
    private DepotAdminService depotAdminService;

    @Autowired
    public BusDepotService(BusDepotRepository busDepotRepository, DepotAdminService depotAdminService) {
        this.busDepotRepository = busDepotRepository;
        this.depotAdminService = depotAdminService;
    }

    public List<BusDepot> findAll() {
        return busDepotRepository.findAll();
    }

    public BusDepot findById(int depotId) {
        Optional<BusDepot> optionalBusDepot = busDepotRepository.findById(depotId);

        if (optionalBusDepot.isPresent()) {
            return optionalBusDepot.get();
        } else {
            throw new RuntimeException("Bus depot not found for id");
        }
    }

    public void save(BusDepot busDepot) {
        busDepotRepository.save(busDepot);
        System.out.println("Bus depot is saved.");
    }

    public void delete(int depotId) {
        busDepotRepository.deleteById(depotId);
        System.out.println("Bus depot is safely deleted.");
    }
}
