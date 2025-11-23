package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.RouteStops;
import com.elevata.gsrtc.service.RouteStopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routeStops")
public class RouteStopsController {
    private RouteStopsService routeStopsService;

    @Autowired
    public RouteStopsController(RouteStopsService routeStopsService) {
        this.routeStopsService = routeStopsService;
    }

    @GetMapping("/routeStops")
    public List<RouteStops> getRouteList() {
        return routeStopsService.findAll();
    }

    @GetMapping("/{stopId}")
    public RouteStops getStopById(@PathVariable int stopId) {
        return routeStopsService.findById(stopId);
    }

    @PostMapping("/add")
    public void add(@RequestBody RouteStops stops) {
        routeStopsService.save(stops);
    }

    @DeleteMapping("/delete/{stopId}")
    public void delete(@PathVariable int stopId) {
        routeStopsService.delete(stopId);
    }
}
