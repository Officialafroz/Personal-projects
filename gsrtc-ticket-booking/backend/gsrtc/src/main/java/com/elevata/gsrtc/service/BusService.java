package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.BusDTO;
import com.elevata.gsrtc.repository.BusRepository;
import com.elevata.gsrtc.entity.Bus;
import com.elevata.gsrtc.entity.BusDepot;
import com.elevata.gsrtc.entity.BusLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    private BusRepository busRepository;
    private BusDepotService busDepotService;
    private BusLayoutService busLayoutService;

    @Autowired
    public BusService(BusRepository busRepository, BusDepotService busDepotService, BusLayoutService busLayoutService) {
        this.busRepository = busRepository;
        this.busDepotService = busDepotService;
        this.busLayoutService = busLayoutService;
    }

    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    public Bus findById(int busId) {
        Optional<Bus> optionalBus = busRepository.findById(busId);

        if (optionalBus.isPresent()) {
            return optionalBus.get();
        } else {
            throw new RuntimeException("Bus not found for id");
        }
    }

    public void save(Bus bus) {
        if (
                bus.getBusDepot() != null &&
                bus.getBusLayout() != null &&
                bus.getBusDepot().getDepotId() > 0 &&
                bus.getBusLayout().getLayoutId() > 0
        ) {
            int depotId = bus.getBusDepot().getDepotId();
            int layoutId = bus.getBusLayout().getLayoutId();

            BusDepot existingDepot = busDepotService.findById(depotId);
            BusLayout existingBusLayout = busLayoutService.findById(layoutId);

            if (existingDepot == null || existingBusLayout == null) {
                throw new RuntimeException(
                        String.format("Invalid depot id - %d or layout id - %d", depotId, layoutId));
            }
            bus.setBusDepot(existingDepot);
            bus.setBusLayout(existingBusLayout);
        }

        busRepository.save(bus);
        System.out.println("Bus is saved.");
    }

    public void delete(int busId) {
        busRepository.deleteById(busId);
        System.out.println("Bus is safely deleted.");
    }

    public BusDTO findByBusNumber(String busNumber) {
        Bus bus = busRepository.findByBusNumber(busNumber);
        BusDTO busDTO = new BusDTO();

        busDTO.setBusNumber(bus.getBusNumber());
        busDTO.setBusType(bus.getBusType());

        return busDTO;
    }
}
