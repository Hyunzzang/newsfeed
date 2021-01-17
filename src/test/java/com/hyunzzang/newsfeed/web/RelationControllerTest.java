package com.hyunzzang.newsfeed.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunzzang.newsfeed.dto.FollowRequest;
import com.hyunzzang.newsfeed.service.RelationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RelationController.class)
public class RelationControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RelationService relationService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void followTest() throws Exception {
    //given
    FollowRequest followRequest = new FollowRequest("test001@gmail.com", "test201@gmail.com");

    //when
    mvc.perform(post("/api/relation/follow")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(followRequest)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("OK"));
  }
}
