package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.DepotAdminLoginDTO;
import com.elevata.gsrtc.entity.DepotAdmin;
import com.elevata.gsrtc.repository.DepotAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepotAdminLoginService {
    private DepotAdminRepository depotAdminRepository;

    @Autowired
    public DepotAdminLoginService(DepotAdminRepository depotAdminRepository) {
        this.depotAdminRepository = depotAdminRepository;
    }


    public String verify(DepotAdminLoginDTO dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        DepotAdmin depotAdmin = depotAdminRepository.findDepotAdminByEmail(email);

        if (depotAdmin.getEmail().equals(email)) {
            if (depotAdmin.getPassword().equals(password)) {
                depotAdmin.setLoggedIn(true);
                return "Verified depot admin";
            }
            return "Incorrect password";
        }
        return "Invalid email";
    }

    public boolean logoutDepotAdmin(int depotId) {
        DepotAdmin admin = depotAdminRepository.findByBusDepotDepotId(depotId);
        admin.setLoggedIn(false); // mark logged-out status if you track it
        return true;
    }
}
