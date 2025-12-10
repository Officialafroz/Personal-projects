package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.PassengerDTO;
import com.elevata.gsrtc.service.BookingPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookingPassenger")
public class BookingPassengerController {
    private BookingPassengerService passengerService;

    @Autowired
    public BookingPassengerController(BookingPassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping("/add")
    public String savePassenger(@RequestBody List<PassengerDTO> passengers,
                                @RequestParam String tripCode,
                                @RequestParam String pnr
    ) {
        return passengerService.save(passengers, tripCode, pnr);
    }

    @GetMapping("/individualFare")
    public double getFare(
            @RequestParam String boardingPoint,
            @RequestParam String destination,
            @RequestParam String tripCode
    ) {
        return passengerService.getFare(boardingPoint, destination, tripCode);
    }
}
