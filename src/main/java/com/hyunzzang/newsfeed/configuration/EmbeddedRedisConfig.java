package com.hyunzzang.newsfeed.configuration;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

@Slf4j
@Configuration
public class EmbeddedRedisConfig {

  @Value("${spring.redis.port}")
  private int redisPort;

  private RedisServer redisServer;

  @PostConstruct
  public void redisServer() throws IOException {
//    RedisExecProvider customProvider = RedisExecProvider.defaultProvider()
//        .override(OS.MAC_OS_X, Architecture.x86, "/Users/hyunzzang/Util/redis-6.0.10")
//        .override(OS.MAC_OS_X, Architecture.x86_64, "/Users/hyunzzang/Util/redis-6.0.10");

    redisServer = RedisServer.builder()
//        .redisExecProvider(customProvider)
        .port(redisPort)
//        .setting("save 300 1")
//        .setting("dbfilename dump.rdb")
//        .setting("dir ./")
//        .setting("appendonly no")
        .build();

    redisServer.start();
  }

  @PreDestroy
  public void stopRedis() {
    if (redisServer != null) {
      redisServer.stop();
    }
  }
}
