package io.pivotal.cloud.stream.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * @author Vinicius Carvalho
 */
@SpringBootApplication
public class HttpSinkApplication {
	public static void main(String[] args) {
		SpringApplication.run(HttpSinkApplication.class,args);
	}

	@Bean
	public AsyncRestTemplate asyncRestTemplate(){
		return new AsyncRestTemplate();
	}
}
