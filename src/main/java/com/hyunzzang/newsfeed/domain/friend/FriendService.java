package com.hyunzzang.newsfeed.domain.friend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FriendService {

  private FriendRepository friendRepository;
  private FriendRedisRepository friendRedisRepository;

  @Autowired
  public FriendService(FriendRepository friendRepository,
      FriendRedisRepository friendRedisRepository) {
    this.friendRepository = friendRepository;
    this.friendRedisRepository = friendRedisRepository;
  }

  @Transient
  public void addFriend(Friend friend) {
    friendRepository.save(friend);
    friendRedisRepository.addFollow(friend.getOwnerId(), friend.getRelationEmail());
    friendRedisRepository.addFollower(friend.getRelationId(), friend.getOwnerEmail());
  }
}
