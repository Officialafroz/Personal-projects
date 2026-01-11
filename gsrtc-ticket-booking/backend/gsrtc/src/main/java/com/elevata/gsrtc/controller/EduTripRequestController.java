package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.EducationalTripRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/edu/trip/req")
public class EducationalTripReqController {
    @PostMapping("/trip-request")
    public ResponseEntity<EducationalTripRequest> createTripRequest(
            @RequestBody EducationalTripRequest tripRequest) {

        EducationalTripRequest savedRequest =
                educationalTripService.createTripRequest(tripRequest);

        return ResponseEntity.ok(savedRequest);
    }

}
