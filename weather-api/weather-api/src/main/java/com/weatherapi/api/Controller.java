package com.weatherapi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    Service service;

    @GetMapping("/weatherData")
    public ResponseEntity<?> getWeatherData(@RequestParam(required = false) String city) {
        if (city == null || city.isEmpty()) {
            return ResponseEntity.badRequest().body("City parameter is missing");
        }
        Model model = (Model) service.getWeatherData(city);
        if (model == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(model);
    }
}