package com.hyunzzang.newsfeed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinResponse {
  private long id;
  private String email;
}
