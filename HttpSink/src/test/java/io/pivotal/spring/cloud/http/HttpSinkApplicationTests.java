package io.pivotal.spring.cloud.http;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HttpSinkApplication.class)
@DirtiesContext
public class HttpSinkApplicationTests {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Test
    public void contextLoads() {
    }

}
