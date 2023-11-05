package com.dailyrefiner.api.service;

import com.dailyrefiner.api.model.controller.NewsData;
import com.dailyrefiner.api.model.reddit.RedditResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Value("#{'${approvedUrls}'.split(',')}")
    List<String> approvedUrls;

    @Value("${redditWorldNewsUrl}")
    private String redditWorldNewsUrl;

    @Value("${redditNationalNewsUrl}")
    private String redditNationalNewsUrl;
    List<NewsData> responseObj;

    private List<NewsData> getWorldNews() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String authToken = AuthService.getToken();
        headers.setBasicAuth(authToken);
        headers.put("User-Agent",
                Collections.singletonList("tomcat:com.dailyrefiner"));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<JSONObject> response
                = restTemplate.exchange(redditWorldNewsUrl, HttpMethod.GET, entity, JSONObject.class);
        JSONObject redditResponseJSONObj = response.getBody();
        Gson gson = new GsonBuilder().create();
        RedditResponse redditResponse = gson.fromJson(String.valueOf(redditResponseJSONObj.toJSONString()), RedditResponse.class);
        return filterResultsWorld(redditResponse.getData().getChildren());
    }

    private List<NewsData> getNationalNews() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String authToken = AuthService.getToken();
        headers.setBasicAuth(authToken);
        headers.put("User-Agent",
                Collections.singletonList("tomcat:com.dailyrefiner"));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<JSONObject> response
                = restTemplate.exchange(redditNationalNewsUrl, HttpMethod.GET, entity, JSONObject.class);
        JSONObject redditResponseJSONObj = response.getBody();
        Gson gson = new GsonBuilder().create();
        RedditResponse redditResponse = gson.fromJson(String.valueOf(redditResponseJSONObj.toJSONString()), RedditResponse.class);
        return filterResultsNational(redditResponse.getData().getChildren());
    }

    public List<NewsData> getNews() {
        List<NewsData> finalResponse = getWorldNews();
        finalResponse.addAll(getNationalNews());
        return finalResponse;
    }


    private List<NewsData> filterResultsWorld(List<RedditResponse.ResponseData.Post> posts){
        responseObj = posts
                .stream()
                .filter(post -> approvedUrls.stream().anyMatch(post.getData().getUrl()::contains))
                .map(post -> new NewsData(post.getData().getUrl(), post.getData().getTitle(), "world"))
                .collect(Collectors.toList());
        return responseObj;
    }

    private List<NewsData> filterResultsNational(List<RedditResponse.ResponseData.Post> posts){
        responseObj = posts
                .stream()
                .filter(post -> approvedUrls.stream().anyMatch(post.getData().getUrl()::contains))
                .map(post -> new NewsData(post.getData().getUrl(), post.getData().getTitle(), "national"))
                .collect(Collectors.toList());
        return responseObj;
    }
}

