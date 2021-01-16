package com.hyunzzang.newsfeed.domain.news;

import com.hyunzzang.newsfeed.domain.user.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column
  private String title;

  @Column
  private String contents;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Builder
  public News(String title, String contents, User user) {
    this.title = title;
    this.contents = contents;
    this.user = user;
  }
}
