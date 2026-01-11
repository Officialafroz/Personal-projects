package com.elevata.gsrtc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalTripRequest
        extends JpaRepository<EducationalTripRequest, Integer> {
}
