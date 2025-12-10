package com.elevata.gsrtc.repository;

import com.elevata.gsrtc.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    List<Passenger> findAllByBookingBookingId(int bookingId);
}
