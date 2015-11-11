package io.pivotal.cloud.stream.hsbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Vinicius Carvalho
 */
@SpringBootApplication
@EnableConfigurationProperties(HawqProperties.class)
public class HawqApplication {
	public static void main(String[] args) {
		SpringApplication.run(HawqApplication.class,args);
	}
}
