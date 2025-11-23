package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.DepotAdminLoginDTO;
import com.elevata.gsrtc.service.DepotAdminLoginService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/depotAdmin")
public class DepotAdminLoginController {
    private DepotAdminLoginService depotAdminLoginService;

    @Autowired
    public DepotAdminLoginController(DepotAdminLoginService depotAdminLoginService) {
        this.depotAdminLoginService = depotAdminLoginService;
    }

    @PostMapping("login/verify")
    public ResponseEntity<DepotAdminLoginDTO> verify(@RequestBody DepotAdminLoginDTO dto) {
        String verificationMessage = depotAdminLoginService.verify(dto);

        if (verificationMessage.equals("Verified depot admin")) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (verificationMessage.equals("Incorrect password")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logout/{depotId}")
    public ResponseEntity<String> logoutDepotAdmin(@PathVariable int depotId) {
        boolean success = depotAdminLoginService.logoutDepotAdmin(depotId);
        if (success) {
            return ResponseEntity.ok("Depot admin logged out successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Logout failed");
        }
    }
}
