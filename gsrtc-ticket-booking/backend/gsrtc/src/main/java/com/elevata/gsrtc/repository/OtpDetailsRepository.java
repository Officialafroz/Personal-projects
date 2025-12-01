package com.elevata.gsrtc.repository;

import com.elevata.gsrtc.entity.OtpDetails;
import com.elevata.gsrtc.entity.User;
import com.elevata.gsrtc.service.OtpDetailsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpDetailsRepository extends JpaRepository<OtpDetails, Integer> {
    Optional<OtpDetails> findOtpDetailsByUser(User user);
    OtpDetails findOtpDetailByOtp(Integer otp);
}
