package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.RouteStopDTO;
import com.elevata.gsrtc.service.BusRouteStopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/busRouteStops")
public class BusRouteStopsController {
    private BusRouteStopsService busRouteStopsService;

    @Autowired
    public BusRouteStopsController(BusRouteStopsService busRouteStopsService) {
        this.busRouteStopsService = busRouteStopsService;
    }

    @GetMapping("/stops/{routeId}")
    public List<RouteStopDTO> findRouteStops(@PathVariable int routeId) {
        return busRouteStopsService.findRouteStops(routeId);
    }

    @PostMapping("/addStop")
    @Transactional
    public String add(@RequestBody RouteStopDTO stopDTO) {
        return busRouteStopsService.save(stopDTO);
    }

    @DeleteMapping("delete/{stopName}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable String stopName) {
        busRouteStopsService.delete(stopName);

        return ResponseEntity.ok("Stop deleted.");
    }
}
