package com.elevata.gsrtc.service;

import org.springframework.stereotype.Service;

@Service
public class ReferenceNumberService {
    public String generatePassengerRef(String pnr, int passengerNo) {
        return pnr + "-P" + passengerNo;
    }
}
