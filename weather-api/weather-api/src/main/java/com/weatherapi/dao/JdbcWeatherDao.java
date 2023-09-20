package com.weatherapi.dao;

import com.weatherapi.model.Weather;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWeatherDao implements WeatherDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcWeatherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveWeather(Weather weather) {
        String insertWeatherSql = "INSERT INTO weather (city_name, temperature_celsius, temperature_fahrenheit, " +
                "humidity, cloud, feels_like_celsius, feels_like_fahrenheit, precipitation_mm, precipitation_inches, " +
                "wind_degree, wind_direction, wind_kph, wind_mph) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(insertWeatherSql,
                weather.getCity(),
                weather.getTemperatureCelsius(),
                weather.getTemperatureFahrenheit(),
                weather.getHumidity(),
                weather.getCloud(),
                weather.getFeelsLikeCelsius(),
                weather.getFeelsLikeFahrenheit(),
                weather.getPrecipitationMm(),
                weather.getPrecipitationInches(),
                weather.getWindDegree(),
                weather.getWindDirection(),
                weather.getWindKph(),
                weather.getWindMph());
    }

    @Override
    public Weather getWeatherByCity(String cityName) {
        // Implement the logic to retrieve weather data for a specific city from the database
        String sql = "SELECT * FROM weather WHERE city = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
        if (results.next()) {
            return mapRowToWeather(results);
        } else {
            return null;
        }
    }

    @Override
    public List<Weather> getAllWeather() {
        List<Weather> weatherList = new ArrayList<>();
        String sql = "SELECT * FROM weather";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Weather weather = mapRowToWeather(results);
            weatherList.add(weather);
        }
        return weatherList;
    }

    @Override
    public void updateWeather(Weather weather) {
        String updateWeatherSql = "UPDATE weather SET temperature = ?, humidity = ?, ... WHERE city = ?";
        jdbcTemplate.update(updateWeatherSql,
                weather.getCity(),
                weather.getTemperatureCelsius(),
                weather.getTemperatureFahrenheit(),
                weather.getHumidity(),
                weather.getCloud(),
                weather.getFeelsLikeCelsius(),
                weather.getFeelsLikeFahrenheit(),
                weather.getPrecipitationMm(),
                weather.getPrecipitationInches(),
                weather.getWindDegree(),
                weather.getWindDirection(),
                weather.getWindKph(),
                weather.getWindMph());
    }

    @Override
    public void deleteWeather(String cityName) {
        // Implement the logic to delete weather data for a specific city from the database
        String deleteWeatherSql = "DELETE FROM weather WHERE city = ?";
        jdbcTemplate.update(deleteWeatherSql, cityName);
    }

    private Weather mapRowToWeather(SqlRowSet rs) {
        Weather weather = new Weather();
        weather.setCity(rs.getString("city_name"));
        weather.setTemperatureCelsius(rs.getDouble("temperature_celsius"));
        weather.setTemperatureFahrenheit(rs.getDouble("temperature_fahrenheit"));
        weather.setHumidity(rs.getInt("humidity"));
        weather.setCloud(rs.getInt("cloud"));
        weather.setFeelsLikeCelsius(rs.getDouble("feels_like_celsius"));
        weather.setFeelsLikeFahrenheit(rs.getDouble("feels_like_fahrenheit"));
        weather.setPrecipitationMm(rs.getDouble("precipitation_mm"));
        weather.setPrecipitationInches(rs.getDouble("precipitation_inches"));
        weather.setWindDegree(rs.getInt("wind_degree"));
        weather.setWindDirection(rs.getString("wind_direction"));
        weather.setWindKph(rs.getDouble("wind_kph"));
        weather.setWindMph(rs.getDouble("wind_mph"));
        return weather;
    }

}
