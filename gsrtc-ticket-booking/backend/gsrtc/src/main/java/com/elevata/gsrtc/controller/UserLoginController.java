package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.EmailRequestDTO;
import com.elevata.gsrtc.dto.OtpRequestDTO;
import com.elevata.gsrtc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/login")
public class UserLoginController {
    private LoginService loginService;

    @Autowired
    public UserLoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/processEmail")
    public void getUserEmail(@RequestBody EmailRequestDTO emailRequestDTO) {
        loginService.processLogin(emailRequestDTO);
    }

    @PostMapping("/verify")
    public String verify(@RequestBody OtpRequestDTO otpRequest) {
        return loginService.verify(otpRequest);
    }
}
