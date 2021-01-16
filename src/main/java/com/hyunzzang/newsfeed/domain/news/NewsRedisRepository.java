package com.hyunzzang.newsfeed.domain.news;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewsRedisRepository {

  private static final String KEY_NEWS = "feed::news::%d";

  private RedisTemplate redisTemplate;

  @Autowired
  public NewsRedisRepository(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void addNews(long userId, long newsId) {
    redisTemplate.opsForZSet().add(makeKeyNews(userId), newsId, newsId);
  }

  public Set<Long> getNews(long userId) {
    return redisTemplate.opsForZSet().reverseRange(makeKeyNews(userId), 0, -1);
  }

  private String makeKeyNews(long id) {
    return String.format(KEY_NEWS, id);
  }
}
