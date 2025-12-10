package com.elevata.gsrtc.repository;

import com.elevata.gsrtc.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByPnr(String pnr);
    List<Booking> findAllByTripTripCode(String tripCode);
    List<Booking> findAllByUserUid(Integer uid);
}
