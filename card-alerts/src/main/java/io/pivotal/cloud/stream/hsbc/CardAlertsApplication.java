package io.pivotal.cloud.stream.hsbc;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(CardAlertsProperties.class)
@EnableScheduling
public class CardAlertsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardAlertsApplication.class, args);
    }


}
