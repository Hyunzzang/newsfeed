package com.hyunzzang.newsfeed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequest {
  private String myEmail;
  private String targetEmail;
}
