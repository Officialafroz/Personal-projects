package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.RouteDTO;
import com.elevata.gsrtc.service.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/busRoute")
public class BusRouteController {
    private BusRouteService busRouteService;

    @Autowired
    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @PostMapping("/addRoute")
    public ResponseEntity<String> addRoute(@RequestBody RouteDTO routeDTO,
                                           @RequestParam String duration) {
        busRouteService.addRoute(routeDTO, duration);
        return ResponseEntity.ok("Route has been added to DB.");
    }

    @GetMapping("/routes")
    public List<RouteDTO> findAll() {
        return busRouteService.findAll();
    }

    @GetMapping("/depotRoutes/{depotId}")
    public List<RouteDTO> findByDepotId(@PathVariable int depotId) {
        return busRouteService.findByDepotId(depotId);
    }
}
