package com.hyunzzang.newsfeed.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsViewResponse {
  private List<NewsView> newsViewList;
}
