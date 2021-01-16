package com.hyunzzang.newsfeed.domain.friend;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "friend")
public class Friend {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column(name = "owner_id")
  private Long ownerId;

  @Transient
  private String ownerEmail;

  @Column(name = "relation_id")
  private Long relationId;

  @Transient
  private String relationEmail;

  @Column(name = "friend_type")
  private FriendType friendType;

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public enum FriendType {
    CONNECT;
  }

  @Builder
  public Friend(long ownerId, String ownerEmail, long relationId, String relationEmail, FriendType friendType) {
    this.ownerId = ownerId;
    this.ownerEmail = ownerEmail;
    this.relationId = relationId;
    this.relationEmail = relationEmail;
    this.friendType = friendType;
  }
}
