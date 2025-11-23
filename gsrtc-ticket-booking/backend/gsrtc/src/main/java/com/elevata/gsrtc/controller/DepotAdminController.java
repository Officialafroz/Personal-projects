package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.DepotAdminDTO;
import com.elevata.gsrtc.entity.DepotAdmin;
import com.elevata.gsrtc.service.DepotAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depotAdmin")
public class DepotAdminController {
    private DepotAdminService depotAdminService;

    @Autowired
    public DepotAdminController(DepotAdminService depotAdminService) {
        this.depotAdminService = depotAdminService;
    }

    @GetMapping("/admins")
    public List<DepotAdmin> getUsersList() {
        return depotAdminService.getDepotAdminList();
    }

    @GetMapping("/{adminId}")
    public DepotAdmin getUser(@PathVariable int adminId) {
        return depotAdminService.findById(adminId);
    }

    @GetMapping("getAdmin/{email}")
    public DepotAdminDTO findByEmail(@PathVariable String email) {
        return depotAdminService.findByEmail(email);
    }

    @PostMapping("/add")
    public void addAdmin(@RequestBody DepotAdmin depotAdmin) {
        depotAdminService.save(depotAdmin);
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteAdmin(@PathVariable int userId) {
        DepotAdmin depotAdmin = depotAdminService.findById(userId);

        if (depotAdmin == null) {
            throw new RuntimeException("User not found for id");
        }

        depotAdminService.deleteById(userId);
        return String.format("User for id %d is deleted.", userId);
    }
}
