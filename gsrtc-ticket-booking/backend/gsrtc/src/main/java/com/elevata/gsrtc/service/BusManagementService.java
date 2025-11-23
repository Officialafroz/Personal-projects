package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.BusDTO;
import com.elevata.gsrtc.entity.Bus;
import com.elevata.gsrtc.entity.BusLayout;
import com.elevata.gsrtc.entity.DepotAdmin;
import com.elevata.gsrtc.repository.BusLayoutRepository;
import com.elevata.gsrtc.repository.BusRepository;
import com.elevata.gsrtc.repository.DepotAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusManagementService {

    private BusRepository busRepository;
    private BusLayoutRepository busLayoutRepository;
    private DepotAdminRepository depotAdminRepository;

    @Autowired
    public BusManagementService(BusRepository busRepository, BusLayoutRepository busLayoutRepository, DepotAdminRepository depotAdminRepository) {
        this.busRepository = busRepository;
        this.busLayoutRepository = busLayoutRepository;
        this.depotAdminRepository = depotAdminRepository;
    }

    public String add(BusDTO busDTO) {
        int busLayoutId = busDTO.getTotalSeats() == 46 ? 1 : busDTO.getTotalSeats() == 45 ? 2 : 3;
        Optional<BusLayout> busLayout = busLayoutRepository.findById(busLayoutId);
        DepotAdmin depotAdmin = depotAdminRepository.findByBusDepotDepotId(busDTO.getDepotId());

        Bus bus = new Bus();
        bus.setBusNumber(busDTO.getBusNumber());
        bus.setBusType(busDTO.getBusType());
        busLayout.ifPresent(bus::setBusLayout);
        bus.setBusDepot(depotAdmin.getBusDepot());

        busRepository.save(bus);

        return "Bus added.";
    }

    public List<BusDTO> findAllBuses(int depotId) {
        List<BusDTO> busDTOS = new ArrayList<>();
        List<Bus> buses = busRepository.findAllByBusDepotDepotId(depotId);


        buses.forEach(bus -> {
            BusDTO busDTO = new BusDTO();

            busDTO.setBusNumber(bus.getBusNumber());
            busDTO.setBusType(bus.getBusType());
            busDTO.setTotalSeats(bus.getBusLayout().getTotalSeats());
            busDTO.setDepotId(depotId);

            busDTOS.add(busDTO);
        });

        return busDTOS;

    }

    public String deleteBus(String busNumber) {
        Bus bus = busRepository.findByBusNumber(busNumber);
        busRepository.deleteById(bus.getBusId());

        return "Bus deleted...";
    }

}
