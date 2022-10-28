package com.dailyrefiner.api.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private static String token;

    @Value("${authUrl}")
    private String authUrl;

    @Value("${redditUser}")
    private String redditUser;

    @Value("${redditPassword}")
    private String redditPassword;


    @Bean
    @EventListener(ApplicationReadyEvent.class)
    public void getAuthToken(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(redditUser, redditPassword);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.put("User-Agent",
                Collections.singletonList("spring.application.dailyrefiner"));
        String body = "grant_type=client_credentials";
        HttpEntity<String> request
                = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                authUrl, request, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        try {
            map.putAll(mapper
                    .readValue(response.getBody(), new TypeReference<Map<String,Object>>(){}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        token = String.valueOf(map.get("access_token"));
    }

    public static String getToken() {
        return token;
    }
}
