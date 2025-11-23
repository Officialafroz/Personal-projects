package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.entity.Refund;
import com.elevata.gsrtc.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refund")
public class RefundController {
    private RefundService refundService;

    @Autowired
    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @GetMapping("/refunds")
    public List<Refund> getRefundList() {
        return refundService.findAll();
    }

    @GetMapping("/{refundId}")
    public Refund getRefundById(@PathVariable int refundId) {
        return refundService.findById(refundId);
    }

    @PostMapping("/add")
    public void add(@RequestBody Refund payment) {
        refundService.save(payment);
    }

    @DeleteMapping("/delete/{refundId}")
    public void delete(@PathVariable int refundId) {
        refundService.delete(refundId);
    }
}
