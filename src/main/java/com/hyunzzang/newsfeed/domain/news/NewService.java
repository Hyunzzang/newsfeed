package com.hyunzzang.newsfeed.domain.news;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class NewService {

  private NewsRepository newsRepository;
  private NewsRedisRepository newsRedisRepository;

  @Autowired
  public NewService(NewsRepository newsRepository, NewsRedisRepository newsRedisRepository) {
    this.newsRepository = newsRepository;
    this.newsRedisRepository = newsRedisRepository;
  }

  public void addNews(News news) {
    News regNews = newsRepository.save(news);
    newsRedisRepository.addNews(news.getUser().getId(), regNews.getId());
  }

  public List<News> getNewsList(long userId) {
    Set<Long> newsIdSet = newsRedisRepository.getNews(userId);

    if (CollectionUtils.isEmpty(newsIdSet)) {
      return Collections.EMPTY_LIST;
    }

    return newsRepository.findAllById(newsIdSet);
  }
}
