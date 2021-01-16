package com.hyunzzang.newsfeed.service;

import com.hyunzzang.newsfeed.domain.news.NewService;
import com.hyunzzang.newsfeed.domain.news.News;
import com.hyunzzang.newsfeed.domain.user.User;
import com.hyunzzang.newsfeed.domain.user.UserRepository;
import com.hyunzzang.newsfeed.dto.NewsRequest;
import com.hyunzzang.newsfeed.dto.NewsView;
import com.hyunzzang.newsfeed.dto.NewsViewResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class NewsIntegrateService {
  private NewService newService;
  private UserRepository userRepository;

  @Autowired
  public NewsIntegrateService(NewService newService, UserRepository userRepository) {
    this.newService = newService;
    this.userRepository = userRepository;
  }

  public void register(NewsRequest newsRequest) {
    User user = userRepository.findByEmail(newsRequest.getCreateEmail());

    // todo: 검증 및 예외 처리?
    if (Objects.isNull(user)) {
      // todo: 예외 처리를 하자.
    }

    News news = News.builder()
        .title(newsRequest.getTitle())
        .contents(newsRequest.getContents())
        .user(user)
        .build();

    newService.addNews(news);
  }

  public NewsViewResponse newsview(String email) {
    User user = userRepository.findByEmail(email);
    if (Objects.isNull(user)) {
      return new NewsViewResponse();
    }

    List<News> newsList = newService.getNewsList(user.getId());
    return toNewsView(newsList);
  }

  private NewsViewResponse toNewsView(List<News> newsList) {
    if (CollectionUtils.isEmpty(newsList)) {
      return new NewsViewResponse();
    }

    List<NewsView> newsViewList = newsList.stream()
        .map(n -> new NewsView(n))
        .collect(Collectors.toList());

    return new NewsViewResponse(newsViewList);
  }
}
