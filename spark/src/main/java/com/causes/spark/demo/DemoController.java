package com.causes.spark.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

  @Autowired
  @Qualifier("baseSparkJob")
  private BaseSparkJob baseSparkJob;

  @GetMapping
  public void get() {
    log.info("Running get");
  }

  @PostMapping
  public void post() {
    log.info("Running post");
  }

  @PostMapping("/spark")
  public void spark(@RequestBody SparkJobConfig config) {
    baseSparkJob.main(config);
  }
}
