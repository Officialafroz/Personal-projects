package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.BusRoute;
import com.elevata.gsrtc.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
public class RouteController {
    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public List<BusRoute> getRouteList() {
        return routeService.findAll();
    }

    @GetMapping("/{routeId}")
    public BusRoute getRouteById(@PathVariable int routeId) {
        return routeService.findById(routeId);
    }

    @PostMapping("/add")
    public void add(@RequestBody BusRoute route) {
        routeService.save(route);
    }

    @DeleteMapping("/delete/{routeId}")
    public void delete(@PathVariable int routeId) {
        routeService.delete(routeId);
    }
}
