package com.hyunzzang.newsfeed.web;

import com.hyunzzang.newsfeed.dto.FollowRequest;
import com.hyunzzang.newsfeed.service.RelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/relation")
public class RelationController {

  private RelationService relationService;

  @Autowired
  public RelationController(RelationService relationService) {
    this.relationService = relationService;
  }

  @PostMapping("/follow")
  public ResponseEntity.BodyBuilder follow(@RequestBody FollowRequest followRequest) {
    log.info(":: follow ::");
    // todo: request 유효성 검증은 시간이 되면~

    relationService.follow(followRequest);

    return ResponseEntity.ok();
  }
}
