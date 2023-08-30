package application.api.service;

import application.api.model.WeatherModel;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.List;

@Component
public class Service {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    public List<WeatherModel> getSearchResults(String query, int days) {

        String url = apiUrl + "?key=" + apiKey + "&q=" + query + "&days=" + days;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        JsonNode jsonNode;
        List<WeatherModel> resultsList = new ArrayList<>();

        try {
            jsonNode = objectMapper.readTree(response.getBody());
            JsonNode resultsNode = jsonNode.path("results");

            for (JsonNode resultNode : resultsNode) {
                // Extract the desired data from the JSON response and create a Model object
                WeatherModel model = new WeatherModel();
                // Set the properties of the model using resultNode
                model.setProperty1(resultNode.path("property1").asText());
                model.setProperty2(resultNode.path("property2").asInt());
                // Add the model to the results list
                resultsList.add(model);
            }

            return resultsList;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}