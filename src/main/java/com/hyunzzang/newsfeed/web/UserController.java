package com.hyunzzang.newsfeed.web;

import com.hyunzzang.newsfeed.dto.UserInfoResponse;
import com.hyunzzang.newsfeed.dto.UserJoinRequest;
import com.hyunzzang.newsfeed.dto.UserJoinResponse;
import com.hyunzzang.newsfeed.domain.user.UserLocalService;
import com.hyunzzang.newsfeed.service.UserIntegrateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

  private UserLocalService userLocalServiceService;
  private UserIntegrateService userIntegrateService;

  @Autowired
  public UserController(UserLocalService userLocalServiceService, UserIntegrateService userIntegrateService) {
    this.userLocalServiceService = userLocalServiceService;
    this.userIntegrateService = userIntegrateService;
  }

  @PostMapping("/join")
  public ResponseEntity<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
    log.info(":: join ::");

    UserJoinResponse userJoinResponse = userLocalServiceService.join(userJoinRequest);
    return ResponseEntity.ok(userJoinResponse);
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable("email") String email) {
    log.info(":: getUserInfo ::");

    return ResponseEntity.ok(userIntegrateService.assembleUserInfo(email));
  }
}
