package com.hyunzzang.newsfeed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {
  private String createEmail;
  private String title;
  private String contents;
}
