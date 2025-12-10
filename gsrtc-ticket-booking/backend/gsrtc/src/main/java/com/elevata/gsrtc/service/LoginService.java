package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.EmailRequestDTO;
import com.elevata.gsrtc.dto.OtpRequestDTO;
import com.elevata.gsrtc.entity.OtpDetails;
import com.elevata.gsrtc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private UserService userService;
    private OtpDetailsService otpDetailsService;
    private EmailService emailService;

    @Autowired
    public LoginService(UserService userService, OtpDetailsService otpDetailsService,
                        EmailService emailService) {
        this.userService = userService;
        this.otpDetailsService = otpDetailsService;
        this.emailService = emailService;
    }

    public void processLogin(EmailRequestDTO emailRequestDTO) {
        String email = emailRequestDTO.getEmail();
        System.out.println(email);

        User user = userService.findUserByEmail(email);

        if (user != null) {
            String otp = String.valueOf(OtpService.generateOtp());

            OtpDetails otpDetail = new OtpDetails();
            otpDetail.setOtp(otp);
            otpDetail.setUser(user);

            otpDetailsService.save(otpDetail);
            emailService.sendEmail(email, "Login code", "Your OTP code: " + otp);
        } else {
            System.out.println(email);
            System.out.println("User with email " + email + " not found.");
        }
    }

    public String verify(OtpRequestDTO otpRequest) {
        String email = otpRequest.getEmail();
        System.out.println(email);
        User user = userService.findUserByEmail(email);

        if (user != null) {
            Optional<OtpDetails> optionalOtpDetails = otpDetailsService.getOtpDetailsByUser(user);

            if (optionalOtpDetails.isPresent()) {
                OtpDetails otpDetail = optionalOtpDetails.get();
                int otpId = otpDetail.getOtpId();

                if (otpDetail.getOtp().equals(otpRequest.getOtp())) {
                    otpDetailsService.deleteById(otpId);
                    return "Verified";
                } else {
                    return "Invalid OTP";
                }
            } else {
                return "OTP not found";
            }
        } else {
            return "User not found";
        }

    }
}