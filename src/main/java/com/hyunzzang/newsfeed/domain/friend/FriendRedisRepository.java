package com.hyunzzang.newsfeed.domain.friend;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class FriendRedisRepository {
  private static final String KEY_FOLLOW = "feed::follow::%d";
  private static final String KEY_FOLLOWER = "feed::follower::%d";

  private RedisTemplate redisTemplate;

  @Autowired
  public FriendRedisRepository(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void addFollow(long ownerId, String email) {
    redisTemplate.opsForSet().add(makeKeyFollow(ownerId), email);
  }

  public Set<String> getFollow(long ownerId) {
    return redisTemplate.opsForSet().members(makeKeyFollow(ownerId));
  }

  public void addFollower(long ownerId, String email) {
    redisTemplate.opsForSet().add(makeKeyFollower(ownerId), email);
  }

  public Set<String> getFollower(long ownerId) {
    return redisTemplate.opsForSet().members(makeKeyFollower(ownerId));
  }


  private String makeKeyFollow(long id) {
    return String.format(KEY_FOLLOW, id);
  }

  private String makeKeyFollower(long id) {
    return String.format(KEY_FOLLOWER, id);
  }
}
