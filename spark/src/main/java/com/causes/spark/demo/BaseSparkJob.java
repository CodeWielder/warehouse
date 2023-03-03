package com.causes.spark.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;

@Slf4j
@Component
public class BaseSparkJob implements Serializable {

  private static SparkLauncher launcher;

  public void main(SparkJobConfig config) {
    before(config);
    run(config);
    after(config);
  }

  private void before(SparkJobConfig config) {
    HashMap<String, String> env = new HashMap<>();
    env.put("JAVA_HOME", SparkJobConfig.JAVA_HOME);
    env.put("SPARK_HOME", SparkJobConfig.SPARK_HOME);

    launcher = new SparkLauncher(env).setAppName("spark").setMaster("local")
        // jar 包所在位置，这里选用了 Spark 官方的 examples jar
        .setAppResource(config.getAppResource())
        // main 方法路径
        .setMainClass(config.getMainClass());
  }

  protected void run(SparkJobConfig config) {
    Process process;
    try {
      process = launcher.launch();
      log(process.getInputStream());
      log(process.getErrorStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void after(SparkJobConfig config) {

  }

  @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
  private static void log(InputStream stream) {
    new Thread(() -> {
      try {
        log.info(StreamUtils.copyToString(stream, Charset.defaultCharset()));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }).start();
  }
}
