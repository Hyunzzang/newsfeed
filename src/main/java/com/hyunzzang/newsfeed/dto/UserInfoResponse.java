package com.hyunzzang.newsfeed.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponse {
  private String email;
  private Set<String> followList;
  private Set<String> followerList;

  @Builder
  public UserInfoResponse(String email, Set<String> followList, Set<String> followerList) {
    this.email = email;
    this.followList = followList;
    this.followerList = followerList;
  }
}
