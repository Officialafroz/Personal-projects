package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.service.SeatMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/seats")
public class SeatMappingController {
    private SeatMappingService seatMappingService;

    @Autowired
    public SeatMappingController(SeatMappingService seatMappingService) {
        this.seatMappingService = seatMappingService;
    }

    @GetMapping("/map")
    public Set<Integer> mapSeats(@RequestParam String tripCode) {
        return seatMappingService.mapSeats(tripCode);
    }

}
