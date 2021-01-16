package com.hyunzzang.newsfeed.web;

import com.hyunzzang.newsfeed.dto.NewsRequest;
import com.hyunzzang.newsfeed.dto.NewsViewResponse;
import com.hyunzzang.newsfeed.service.NewsIntegrateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/news")
public class NewsController {

  private NewsIntegrateService newsIntegrateService;

  @Autowired
  public NewsController(NewsIntegrateService newsIntegrateService) {
    this.newsIntegrateService = newsIntegrateService;
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody NewsRequest newsRequest) {
    log.info(":: register ::");
    // todo: request 유효성 검증은 시간이 되면~

    newsIntegrateService.register(newsRequest);

    return ResponseEntity.ok("OK");
  }


  @GetMapping("/{email}")
  public ResponseEntity<NewsViewResponse> newsfeed(@PathVariable("email") String email) {
    log.info(":: newsfeed ::");
    return ResponseEntity.ok(newsIntegrateService.newsview(email));
  }
}
