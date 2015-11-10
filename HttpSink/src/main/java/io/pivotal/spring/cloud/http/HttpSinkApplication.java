package io.pivotal.spring.cloud.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses=HttpSink.class)
public class HttpSinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpSinkApplication.class, args);
    }
}
