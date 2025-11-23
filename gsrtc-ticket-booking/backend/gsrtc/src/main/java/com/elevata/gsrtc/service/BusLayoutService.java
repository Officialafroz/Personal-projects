package com.elevata.gsrtc.service;

import com.elevata.gsrtc.repository.BusLayoutRepository;
import com.elevata.gsrtc.entity.BusLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusLayoutService {
    private BusLayoutRepository busLayoutRepository;

    @Autowired
    public BusLayoutService(BusLayoutRepository busLayoutRepository) {
        this.busLayoutRepository = busLayoutRepository;
    }

    public List<BusLayout> findAll() {
        return busLayoutRepository.findAll();
    }

    public BusLayout findById(int layoutId) {
        Optional<BusLayout> optionalBusLayout = busLayoutRepository.findById(layoutId);

        if (optionalBusLayout.isPresent()) {
            return optionalBusLayout.get();
        } else {
            throw new RuntimeException("Bus layout not found for id");
        }
    }

    public void save(BusLayout BusLayout) {
        busLayoutRepository.save(BusLayout);
        System.out.println("Bus Layout is saved.");
    }

    public void delete(int layoutId) {
        busLayoutRepository.deleteById(layoutId);
        System.out.println("Bus layout is safely deleted.");
    }
}

