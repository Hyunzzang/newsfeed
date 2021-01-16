package com.hyunzzang.newsfeed.service;

import com.hyunzzang.newsfeed.domain.friend.FriendRedisRepository;
import com.hyunzzang.newsfeed.domain.user.User;
import com.hyunzzang.newsfeed.domain.user.UserRepository;
import com.hyunzzang.newsfeed.dto.UserInfoResponse;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIntegrateService {

  private FriendRedisRepository friendRedisRepository;
  private UserRepository userRepository;

  @Autowired
  public UserIntegrateService(FriendRedisRepository friendRedisRepository,
      UserRepository userRepository) {
    this.friendRedisRepository = friendRedisRepository;
    this.userRepository = userRepository;
  }

  public UserInfoResponse assembleUserInfo(String email) {
    User user = userRepository.findByEmail(email);

    // todo: user의 검증및 예외 처리는 시간 될 때

    Set<String> follow = friendRedisRepository.getFollow(user.getId());
    Set<String> follower = friendRedisRepository.getFollower(user.getId());

    return UserInfoResponse.builder()
        .email(email)
        .followList(follow)
        .followerList(follower)
        .build();
  }
}
