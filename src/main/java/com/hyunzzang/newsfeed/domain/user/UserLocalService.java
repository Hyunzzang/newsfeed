package com.hyunzzang.newsfeed.domain.user;

import com.hyunzzang.newsfeed.dto.UserJoinRequest;
import com.hyunzzang.newsfeed.dto.UserJoinResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserLocalService {

  private UserRepository userRepository;

  @Autowired
  public UserLocalService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public UserJoinResponse join(UserJoinRequest userJoinRequest) {
    User user = User.builder()
        .email(userJoinRequest.getEmail())
        .pw(userJoinRequest.getPw())
        .build();

    user = userRepository.save(user);

    return new UserJoinResponse(user.getId(), user.getEmail());
  }
}
