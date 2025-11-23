package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.BusDTO;
import com.elevata.gsrtc.entity.Bus;
import com.elevata.gsrtc.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus")
public class BusController {
    private BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/buses")
    public List<Bus> getBusList() {
        return busService.findAll();
    }

    @GetMapping("/{busId}")
    public Bus getBus(@PathVariable int busId) {
        return busService.findById(busId);
    }

    @GetMapping("/{busNumber}")
    public ResponseEntity<BusDTO> findByBusNumber(@PathVariable String busNumber) {
        return new ResponseEntity<>(busService.findByBusNumber(busNumber), HttpStatus.OK);
    }

    @PostMapping("/add")
    public void add(@RequestBody Bus bus) {
        String formattedNumber = bus.getBusNumber();
        if (!formattedNumber.matches("^GJ\\d{2}[A-Z]{1,2}\\d{1,4}$")) {
            throw new IllegalArgumentException("Invalid bus number.");
        }

        busService.save(bus);
    }

    @DeleteMapping("/delete/{busId}")
    public void delete(@PathVariable int busId) {
        busService.delete(busId);
    }
}
