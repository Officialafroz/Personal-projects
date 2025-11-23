package com.elevata.gsrtc.service;

import com.elevata.gsrtc.repository.OtpDetailsRepository;
import com.elevata.gsrtc.entity.OtpDetails;
import com.elevata.gsrtc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OtpDetailsService {
    private OtpDetailsRepository otpDetailsRepository;

    @Autowired
    public OtpDetailsService(OtpDetailsRepository otpDetailsRepository) {
        this.otpDetailsRepository = otpDetailsRepository;
    }

    public List<OtpDetails> getOtpList() {
        return otpDetailsRepository.findAll();
    }


    public OtpDetails findOtpDetailsById(int otpId) {
        Optional<OtpDetails> otpDetails = otpDetailsRepository.findById(otpId);

        if (otpDetails.isPresent()) {
            return otpDetails.get();
        } else {
            throw new RuntimeException("Otp details not found for id");
        }
    }

//    public List<OtpDetails> findOtpDetailsListByUserId(int userId) {
//        return otpDetailsRepository.findAllById(userId);
//    }

    public void save(OtpDetails otpDetails) {
        otpDetailsRepository.save(otpDetails);
        System.out.println("User has been added.");
    }

    public void deleteById(int otpId) {
        otpDetailsRepository.deleteById(otpId);
    }

    public Optional<OtpDetails> getOtpDetailsByUser(User user) {
        return otpDetailsRepository.findOtpDetailsByUser(user);
    }
}
