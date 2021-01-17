package com.hyunzzang.newsfeed.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunzzang.newsfeed.domain.user.UserLocalService;
import com.hyunzzang.newsfeed.dto.UserInfoResponse;
import com.hyunzzang.newsfeed.dto.UserJoinRequest;
import com.hyunzzang.newsfeed.dto.UserJoinResponse;
import com.hyunzzang.newsfeed.service.UserIntegrateService;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserLocalService userLocalServiceService;

  @MockBean
  private UserIntegrateService userIntegrateService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void joinTest() throws Exception {
    //given
    UserJoinRequest userJoinRequest = new UserJoinRequest("test001@gmail.com", "abcd123");
    UserJoinResponse userJoinResponse = new UserJoinResponse(1, "test001@gmail.com");

    given(userLocalServiceService.join(any(UserJoinRequest.class))).willReturn(userJoinResponse);

    //when
    MvcResult mvcResult = mvc.perform(post("/api/user/join")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userJoinRequest)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(1))
        .andExpect(jsonPath("email").value("test001@gmail.com"))
        .andReturn();

    //then
  }

  @Test
  public void getUserInfoTest() throws Exception {
    //given
    String email = "test001@gmail.com";
    UserInfoResponse userInfoResponse = UserInfoResponse.builder()
        .email(email)
        .followList(new HashSet<String>(Arrays.asList("5638")))
        .followerList(new HashSet<String>(Arrays.asList("7896")))
        .build();

    given(userIntegrateService.assembleUserInfo(email)).willReturn(userInfoResponse);

    //when
    mvc.perform(get("/api/user/" + email)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("email").value(email))
        .andExpect(jsonPath("followList[0]").value("5638"))
        .andExpect(jsonPath("followerList[0]").value("7896"));
  }
}
