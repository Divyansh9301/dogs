package com.tinyfur.auth.controller;

import com.tinyfur.auth.entity.City;
import com.tinyfur.auth.entity.State;
import com.tinyfur.auth.repository.CityRepository;
import com.tinyfur.auth.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/states")
    public ResponseEntity<List<State>> getAllStates() {
        List<State> states = stateRepository.findAll();
        return ResponseEntity.ok(states);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/cities/state/{stateId}")
    public ResponseEntity<List<City>> getCitiesByState(@PathVariable Integer stateId) {
        List<City> cities = cityRepository.findByStateStateId(stateId);
        return ResponseEntity.ok(cities);
    }
} 