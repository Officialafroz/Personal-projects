package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.Trip;
import com.elevata.gsrtc.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    private TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/trips")
    public List<Trip> getTripList() {
        return tripService.findAll();
    }

    @GetMapping("/{tripId}")
    public Trip getTripById(@PathVariable int tripId) {
        return tripService.findById(tripId);
    }

    @PostMapping("/add")
    public void add(@RequestBody Trip trip) {
        tripService.save(trip);
    }

    @DeleteMapping("/delete/{tripId}")
    public void delete(@PathVariable int tripId) {
        tripService.delete(tripId);
    }
}
