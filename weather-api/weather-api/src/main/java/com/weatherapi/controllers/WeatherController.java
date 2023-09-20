package com.weatherapi.controllers;

import com.weatherapi.dao.WeatherDao;
import com.weatherapi.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherDao weatherDao;

    @Autowired
    public WeatherController(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    @PostMapping
    public ResponseEntity<String> addWeather(@RequestBody Weather weather) {
        weatherDao.saveWeather(weather);
        return new ResponseEntity<>("Weather data added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<Weather> getWeatherByCity(@PathVariable String cityName) {
        Weather weather = weatherDao.getWeatherByCity(cityName);
        if (weather != null) {
            return new ResponseEntity<>(weather, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Weather>> getAllWeather() {
        List<Weather> allWeather = weatherDao.getAllWeather();
        return new ResponseEntity<>(allWeather, HttpStatus.OK);
    }

    @PutMapping("/{cityName}")
    public ResponseEntity<String> updateWeather(@PathVariable String cityName, @RequestBody Weather weather) {
        Weather existingWeather = weatherDao.getWeatherByCity(cityName);
        if (existingWeather != null) {
            weatherDao.updateWeather(weather);
            return new ResponseEntity<>("Weather data updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Weather data not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cityName}")
    public ResponseEntity<String> deleteWeather(@PathVariable String cityName) {
        Weather existingWeather = weatherDao.getWeatherByCity(cityName);
        if (existingWeather != null) {
            weatherDao.deleteWeather(cityName);
            return new ResponseEntity<>("Weather data deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Weather data not found", HttpStatus.NOT_FOUND);
        }
    }
}
