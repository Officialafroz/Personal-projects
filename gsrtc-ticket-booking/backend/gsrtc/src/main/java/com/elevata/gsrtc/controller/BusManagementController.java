package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.BusDTO;
import com.elevata.gsrtc.service.BusManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus")
public class BusManagementController {
    private BusManagementService busManagementService;

    @Autowired
    public BusManagementController(BusManagementService busManagementService) {
        this.busManagementService = busManagementService;
    }

    @GetMapping("/busList/{depotId}")
    public List<BusDTO> findAllBuses(@PathVariable int depotId) {
        return busManagementService.findAllBuses(depotId);
    }

    @PostMapping("/addBus")
    public String addBus(@RequestBody BusDTO busDTO) {
        String formattedNumber = busDTO.getBusNumber();
        if (!formattedNumber.matches("^GJ\\d{2}[A-Z]{1,2}\\d{1,4}$")) {
            throw new IllegalArgumentException("Invalid bus number.");
        }
        return busManagementService.add(busDTO);
    }

    @DeleteMapping("/deleteBus/{busNumber}")
    public String deleteBus(@PathVariable String busNumber) {
        return busManagementService.deleteBus(busNumber);
    }
}
