package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.BusDepot;
import com.elevata.gsrtc.service.BusDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/busDepot")
public class BusDepotController {
    private BusDepotService busDepotService;

    @Autowired
    public BusDepotController(BusDepotService busDepotService) {
        this.busDepotService = busDepotService;
    }

    @GetMapping("/depots")
    public List<BusDepot> getDepotList() {
        return busDepotService.findAll();
    }

    @GetMapping("/depot/{depotId}")
    public BusDepot getDepot(@PathVariable int depotId) {
        return busDepotService.findById(depotId);
    }

    @PostMapping("/add")
    public void add(@RequestBody BusDepot busDepot) {
        busDepotService.save(busDepot);
    }

    @DeleteMapping("/delete/{depotId}")
    public void remove(@PathVariable int depotId) {
        busDepotService.delete(depotId);
    }
}
