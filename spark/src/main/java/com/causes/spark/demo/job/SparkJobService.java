package com.causes.spark.demo.job;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SparkJobService {

  @Autowired
  private ApplicationContext applicationContext;

  public void run(String app) {
    BaseSparkJob sparkJob = applicationContext.getBean(StringUtils.uncapitalize(app), BaseSparkJob.class);
    sparkJob.main(null);
  }
}
