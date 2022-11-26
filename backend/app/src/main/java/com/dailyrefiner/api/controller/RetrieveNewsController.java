package com.dailyrefiner.api.controller;

import com.dailyrefiner.api.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class RetrieveNewsController {

    @Autowired
    NewsService newsService;

    @ResponseBody
    @GetMapping(value="/getNews")
    private List<String> getNewsUrls()  {
        return newsService.getNews();
    }
}
