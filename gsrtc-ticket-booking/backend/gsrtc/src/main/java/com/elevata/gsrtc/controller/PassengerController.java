package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.Passenger;
import com.elevata.gsrtc.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/passengers")
    public List<Passenger> findAll() {
        return passengerService.findAll();
    }

    @GetMapping("/{passengerId}")
    public Passenger findById(@PathVariable int passengerId) {
        return passengerService.findById(passengerId);
    }

    @PostMapping("/add")
    public void add(@RequestBody Passenger passenger) {
        passengerService.save(passenger);
    }

    @DeleteMapping("/delete/{passengerId}")
    public void delete(@PathVariable int passengerId) {
        passengerService.delete(passengerId);
    }
}
