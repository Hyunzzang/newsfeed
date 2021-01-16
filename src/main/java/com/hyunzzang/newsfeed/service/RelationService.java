package com.hyunzzang.newsfeed.service;

import com.hyunzzang.newsfeed.domain.friend.Friend;
import com.hyunzzang.newsfeed.domain.friend.FriendService;
import com.hyunzzang.newsfeed.domain.user.User;
import com.hyunzzang.newsfeed.domain.user.UserRepository;
import com.hyunzzang.newsfeed.dto.FollowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationService {

  private UserRepository userRepository;
  private FriendService friendService;

  @Autowired
  public RelationService(UserRepository userRepository, FriendService friendService) {
    this.userRepository = userRepository;
    this.friendService = friendService;
  }

  public void follow(FollowRequest followRequest) {
    User ownerUser = userRepository.findByEmail(followRequest.getMyEmail());
    User targetUser = userRepository.findByEmail(followRequest.getTargetEmail());

    // todo: user 정보가 없을 경우의 예외 상항은 시간이 되면 처리 하자.
    // todo: 중복 제한도 처리~

    Friend friend = Friend.builder()
        .ownerId(ownerUser.getId())
        .ownerEmail(ownerUser.getEmail())
        .relationId(targetUser.getId())
        .relationEmail(targetUser.getEmail())
        .friendType(Friend.FriendType.CONNECT)
        .build();

    friendService.addFriend(friend);
  }
}
