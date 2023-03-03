package com.causes.spark.demo;

import lombok.Data;

@Data
public class SparkJobConfig {

  public static final String SPARK_HOME = "/opt/module/spark-3.1.2-bin-hadoop3.2";
  public static final String JAVA_HOME = "/usr/java/jdk8";

  private String appResource;
  private String mainClass;
}
