package com.elevata.gsrtc.service;

import com.elevata.gsrtc.dto.EmailRequestDTO;
import com.elevata.gsrtc.dto.OtpRequestDTO;
import com.elevata.gsrtc.entity.RegistrationOtp;
import com.elevata.gsrtc.repository.RegistrationOtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private RegistrationOtpRepository otpRepository;
    private EmailService emailService;

    @Autowired
    public RegistrationService(RegistrationOtpRepository otpRepository, EmailService emailService) {
        this.otpRepository = otpRepository;
        this.emailService = emailService;
    }

    public void processRegistration(EmailRequestDTO emailRequestDTO) {
        String email = emailRequestDTO.getEmail();
        System.out.println(email);

        int otp = OtpService.generateOtp();
        RegistrationOtp registrationOtp = new RegistrationOtp(otp, email);

        otpRepository.save(registrationOtp);
        emailService.sendEmail(email,
                "Registration code", "Your OTP code is " + otp);
    }

    public String verify(OtpRequestDTO otpRequest) {
        String email = otpRequest.getEmail();
        RegistrationOtp registrationOtp = otpRepository.findByEmail(otpRequest.getEmail());

        if (registrationOtp != null) {
            return "User verified";
        }
        return "Invalid email";
    }
}
