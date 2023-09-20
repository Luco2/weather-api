package com.weatherapi.dao;

import com.weatherapi.model.Weather;

import java.util.List;

public interface WeatherDao {
    void saveWeather(Weather weather);

    Weather getWeatherByCity(String cityName);

    List<Weather> getAllWeather();

    void updateWeather(Weather weather);

    void deleteWeather(String cityName);
}
