package com.causes.spark;

import com.causes.spark.demo.job.SparkJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class SparkApplication implements ApplicationRunner {

  @Autowired
  private SparkJobService sparkJobService;

  public static void main(String[] args) {
    SpringApplication.run(SparkApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    // TODO: 测试，暂时写死为 SparkPiJob
    sparkJobService.run("SparkPiJob");
  }
}
