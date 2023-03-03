package com.causes.spark;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class SparkApplication implements ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(SparkApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    List<String> appArgs = args.getNonOptionArgs().stream().filter(arg -> arg.contains("app")).collect(Collectors.toList());
    if (CollectionUtils.isEmpty(appArgs)) {
      return;
    }
    String appType = appArgs.get(0).split("=")[1];
    System.out.println(appType);
    log.info("Running application method");
  }
}
