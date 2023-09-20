package com.weatherapi.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Service {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    public List<Model> getWeatherData(String city) {
        try {
            String url = apiUrl + "/current.json?key=" + apiKey + "&q=" + city;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);

            HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            String cityName = jsonNode.path("location").path("name").asText();
            String country = jsonNode.path("location").path("country").asText();
            double temperatureCelsius = jsonNode.path("current").path("temp_c").asDouble();
            double temperatureFahrenheit = jsonNode.path("current").path("temp_f").asDouble();
            double windMph = jsonNode.path("current").path("wind_mph").asDouble();
            double windKph = jsonNode.path("current").path("wind_kph").asDouble();
            int windDegree = jsonNode.path("current").path("wind_degree").asInt();
            String windDirection = jsonNode.path("current").path("wind_dir").asText();
            double precipitationMm = jsonNode.path("current").path("precip_mm").asDouble();
            double precipitationInches = jsonNode.path("current").path("precip_in").asDouble();
            int humidity = jsonNode.path("current").path("humidity").asInt();
            int cloud = jsonNode.path("current").path("cloud").asInt();
            double feelsLikeCelsius = jsonNode.path("current").path("feelslike_c").asDouble();
            double feelsLikeFahrenheit = jsonNode.path("current").path("feelslike_f").asDouble();

            // Create and return list of weather data
            List<Model> weatherDataList = new ArrayList<>();
            Model weatherData = new Model(cityName, country, temperatureCelsius, temperatureFahrenheit,
                    windMph, windKph, windDegree, windDirection, precipitationMm, precipitationInches, humidity,
                    cloud, feelsLikeCelsius, feelsLikeFahrenheit);
            weatherDataList.add(weatherData);
            return weatherDataList;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
