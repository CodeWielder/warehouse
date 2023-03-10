package com.causes.spark.demo.job;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public abstract class BaseSparkJob {

  protected SparkSession spark;
  protected JavaSparkContext jsc;

  @Autowired
  private ApplicationContext applicationContext;

  public void main(Map<String, Object> args) {
    init(args);
    beforeRun();
    run(args);
    afterRun();
  }

  protected void init(Map<String, Object> args) {
    SparkSession.Builder builder = SparkSession.builder().master("local[*]").appName("spark");
    // TODO: 其他配置
    spark = builder.getOrCreate();
    jsc = new JavaSparkContext(spark.sparkContext());
  }

  protected void beforeRun() {
  }

  protected abstract void run(Map<String, Object> args);

  protected void afterRun() {
    ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
    context.close();
  }
}
