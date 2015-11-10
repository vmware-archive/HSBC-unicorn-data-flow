package io.pivotal.cloud.stream.hsbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JpaBaseConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CardAlertsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardAlertsApplication.class, args);
    }
}
