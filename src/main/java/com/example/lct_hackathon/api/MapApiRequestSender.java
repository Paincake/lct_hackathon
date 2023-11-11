package com.example.lct_hackathon.api;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapApiRequestSender {
    public List<Double> getLonLatByAddress(String address){
        String pos = "";
        String url = String.format("https://geocode-maps.yandex.ru/1.x?apikey=f09ffb11-edfe-464c-9ebc-3440e317a9a8&geocode=%s&format=json", address);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(result);
            String[] lonLat = rootNode.get("response").get("GeoObjectCollection").get("featureMember").findPath("Point").get("pos").asText().split(" ");
            return List.of(Double.parseDouble(lonLat[0]), Double.parseDouble(lonLat[1]));
        }
        catch (Exception exc){}
        return null;
    }
}
