package io.pivotal.cloud.stream.hsbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Vinicius Carvalho
 */
@SpringBootApplication
@EnableConfigurationProperties(CustomerProcessorProperties.class)
public class CustomerProcessorApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerProcessorApplication.class,args);
	}
}
