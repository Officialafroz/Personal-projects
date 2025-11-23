package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.OtpDetails;
import com.elevata.gsrtc.service.OtpDetailsService;
import com.elevata.gsrtc.service.OtpService;
import com.elevata.gsrtc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/otpDetails")
public class OtpDetailsController {

    private OtpDetailsService otpDetailsService;
    private UserService userService;
    private OtpService otpService;

    @Autowired
    public OtpDetailsController(OtpDetailsService otpDetailsService,
                                UserService userService, OtpService otpService) {
        this.otpDetailsService = otpDetailsService;
        this.userService = userService;
        this.otpService = otpService;
    }

    @GetMapping("/otps")
    public List<OtpDetails> getOtpList() {
        return otpDetailsService.getOtpList();
    }

    @GetMapping("/{otpId}")
    public OtpDetails getOtpDetailsByUserId(@PathVariable int otpId) {
         return otpDetailsService.findOtpDetailsById(otpId);
    }

    @PostMapping("/add")
    public void add(@RequestBody OtpDetails otpDetails) {
        otpDetailsService.save(otpDetails);
    }

    @PostMapping("/send/{userId}")
    public void sendOtp(@PathVariable int userId) {
        String email = userService.findById(userId).getEmail();

//        otpDetailsService.save();
//        otpService.sendOtp(email);
    }

    @DeleteMapping("/delete/{otpId}")
    public String delete(@PathVariable int otpId) {
        OtpDetails otpDetails = otpDetailsService.findOtpDetailsById(otpId);

        if (otpDetails == null) {
            throw new RuntimeException("User not found for id");
        }

        otpDetailsService.deleteById(otpId);
        return String.format("User for id %d is deleted.", otpId);
    }
}
