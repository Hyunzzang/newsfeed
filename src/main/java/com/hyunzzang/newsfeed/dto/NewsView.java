package com.hyunzzang.newsfeed.dto;

import com.hyunzzang.newsfeed.domain.news.News;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class NewsView {
  private String title;
  private String contents;
  private String ownerEmail;
  private LocalDateTime createdAt;

  public NewsView(News news) {
    this.title = news.getTitle();
    this.contents = news.getContents();
    this.ownerEmail = news.getUser().getEmail();
    this.createdAt = news.getCreatedAt();
  }
}
