package ru.onalex.news.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.onalex.news.models.Article;
import ru.onalex.news.models.NewsApiResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@RestController
@RequestMapping("/getnews")

public class HomeController {
//получаем новости с агрегатора по запросу (computer, java и т.п.) и получаем результаты на заданных языках, начиная с установленной даты
//результаты выводятся в порядке убывания даты (от новых к старым)
    private final String baseUrl = "https://newsapi.org/v2/everything";
    private final String apiKey = "ae0595727e284fb3be698f5c3732b8ae";
    private final String from = "2025-02-22"; //
    private final List<String> langs = new ArrayList<>();

    {
        langs.add("en");
        langs.add("ru");
        langs.add("it");
    }

    @GetMapping("/{query}")
    public ResponseEntity<NewsApiResponse> search(@PathVariable String query) {

        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + "?q=" + query + "&apiKey=" + apiKey + "&from=" + from + "&language=";
        ArrayList<Article> arr = new ArrayList<>();
        int totalArticles = 0;
        NewsApiResponse resultData = new NewsApiResponse();

        for (String lang : langs) {
            System.out.println(url+lang);
            ResponseEntity<NewsApiResponse> response = restTemplate.getForEntity(url+lang, NewsApiResponse.class);

            if(response.getStatusCode().is2xxSuccessful()){
                NewsApiResponse result = response.getBody();
                if(result != null){
                    arr.addAll(result.articles);
                    totalArticles += result.totalResults;
                }
            }else
                return ResponseEntity.notFound().build();
        }
        arr.sort((a1, a2) -> a2.newsDate.compareTo(a1.newsDate));
        System.out.println("Last article of: " + arr.get(0).newsDate + ", eldest article of: " + arr.get(arr.size()-1).newsDate);
        resultData.articles = arr;

        resultData.totalResults = totalArticles;
        return ResponseEntity.ok(resultData);
    }
}
