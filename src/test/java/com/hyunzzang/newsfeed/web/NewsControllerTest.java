package com.hyunzzang.newsfeed.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunzzang.newsfeed.domain.news.News;
import com.hyunzzang.newsfeed.domain.user.User;
import com.hyunzzang.newsfeed.dto.NewsRequest;
import com.hyunzzang.newsfeed.dto.NewsView;
import com.hyunzzang.newsfeed.dto.NewsViewResponse;
import com.hyunzzang.newsfeed.dto.UserInfoResponse;
import com.hyunzzang.newsfeed.service.NewsIntegrateService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(NewsController.class)
public class NewsControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private NewsIntegrateService newsIntegrateService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void registerTest() throws Exception {
    //given
    NewsRequest newsRequest = new NewsRequest("test001@gmail.com", "제목", "내용");

    //when
    mvc.perform(post("/api/news/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newsRequest)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("OK"));
  }


  @Test
  public void newsfeedTest() throws Exception {
    //given
    String email = "test001@gmail.com";
    News news = News.builder()
        .title("제목~1")
        .contents("내용무")
        .user(User.builder().email("test123@gmail.com").pw("12345").build())
        .build();
    ReflectionTestUtils.setField(news, "createdAt", LocalDateTime.now());

    NewsView newsView = new NewsView(news);
    NewsViewResponse newsViewResponse = new NewsViewResponse(Arrays.asList(newsView));

    given(newsIntegrateService.newsview(email)).willReturn(newsViewResponse);

    //when
    mvc.perform(get("/api/news/" + email)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("newsViewList[0].title").value("제목~1"))
        .andExpect(jsonPath("newsViewList[0].contents").value("내용무"));
  }
}
