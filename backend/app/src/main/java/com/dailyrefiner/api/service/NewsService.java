package com.dailyrefiner.api.service;

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

    @Value("${redditUrl}")
    private String redditUrl;

    public List<String> getNews(){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String authToken = AuthService.getToken();
        headers.setBearerAuth(authToken);
        headers.put("User-Agent",
                Collections.singletonList("tomcat:com.dailyrefiner"));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<JSONObject> response
                = restTemplate.exchange(redditUrl, HttpMethod.GET, entity, JSONObject.class);
        JSONObject redditResponseJSONObj = response.getBody();
        Gson gson = new GsonBuilder().create();
        RedditResponse redditResponse = gson.fromJson(String.valueOf(redditResponseJSONObj.toJSONString()), RedditResponse.class);
        return filterResults(redditResponse.getData().getChildren());
    }
    private List<String> filterResults(List<RedditResponse.ResponseData.Post> posts){
        return posts
                .stream()
                .filter(post -> approvedUrls.stream().anyMatch(post.getData().getUrl()::contains))
                .map(post -> post.getData().getUrl())
                .collect(Collectors.toList());
    }
}

