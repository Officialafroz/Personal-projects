package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.BusTripDTO;
import com.elevata.gsrtc.dto.TripDTO;
import com.elevata.gsrtc.entity.Trip;
import com.elevata.gsrtc.service.BusTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/busTrip")
public class BusTripController {

    private BusTripService busTripService;

    @Autowired
    public BusTripController(BusTripService busTripService) {
        this.busTripService = busTripService;
    }

    @GetMapping("/tripCode")
    public String findTripCode() {
        return busTripService.generateTripCode("0050", "Gandhinagar", "Surat", "Premium", 2);
    }

    @GetMapping("/trips/{depotId}")
    public List<TripDTO> findAllByDepotId(@PathVariable int depotId) {
        return busTripService.findAllByDepotId(depotId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTrip(@RequestBody BusTripDTO busTripDTO) {
        busTripService.save(busTripDTO);
        return ResponseEntity.ok("Bus Trip added.");
    }
}
