package com.elevata.gsrtc.controller;

import com.elevata.gsrtc.dto.SearchDTO;
import com.elevata.gsrtc.dto.SearchResultDTO;
import com.elevata.gsrtc.dto.TripDTO;
import com.elevata.gsrtc.service.SearchBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/searchBus")
public class SearchResultController {
    private SearchBusService searchBusService;

    @Autowired
    public SearchResultController(SearchBusService searchBusService) {
        this.searchBusService = searchBusService;
    }

    @GetMapping("/results")
    public List<SearchResultDTO> getBuses(
            @RequestParam LocalDate journeyDate,
            @RequestParam String source,
            @RequestParam String destination
    ) {
        return searchBusService.getSearchResult(journeyDate, source, destination);
    }
}
