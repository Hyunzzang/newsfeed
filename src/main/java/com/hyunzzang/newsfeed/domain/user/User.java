package com.hyunzzang.newsfeed.domain.user;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column
  private String email;

  @Column
  private String pw;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Builder
  public User(String email, String pw) {
    Assert.notNull(email, "이메일 정보가 없습니다.");
    Assert.notNull(pw, "패스워드 정보가 없습니다.");

    this.email = email;
    this.pw = pw;
  }
}
