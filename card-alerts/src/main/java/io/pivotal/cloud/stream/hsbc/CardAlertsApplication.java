package io.pivotal.cloud.stream.hsbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CardAlertsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardAlertsApplication.class, args);
    }
}
