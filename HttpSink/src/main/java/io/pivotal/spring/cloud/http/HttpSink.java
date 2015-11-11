package io.pivotal.spring.cloud.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.spring.cloud.http.domain.Custom;
import io.pivotal.spring.cloud.http.domain.Ios;
import io.pivotal.spring.cloud.http.domain.Target;
import io.pivotal.spring.cloud.http.domain.Message;
import io.pivotal.spring.cloud.http.domain.Offer;
import io.pivotal.spring.cloud.http.domain.PushNotification;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBinding(Sink.class)
@EnableConfigurationProperties(HttpSinkOptionsMetadata.class)
public class HttpSink {

    private static Logger logger = LoggerFactory.getLogger(HttpSink.class);

    //@Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private HttpSinkOptionsMetadata options;

    @Autowired
    ObjectMapper mapper;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void httpSink(Object payload) {
        logger.info("Received: " + payload);
        String input = payload.toString();
        try {
            Offer offer = mapper.readValue(input, Offer.class);

            HttpHeaders headers = new HttpHeaders();

            if (options.getCredentials() != null) {
                String plainCreds = options.getCredentials();
                byte[] plainCredsBytes = plainCreds.getBytes();
                byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
                String base64Creds = new String(base64CredsBytes);

                headers.add("Authorization", "Basic " + base64Creds);
            }
            /*Ios ios = new Ios();
             ios.getAlert().put("body", "A new offer is available");
             Custom custom = new Custom();
             custom.setIos(ios);
             */
            Message message = new Message();
            message.setBody(input);

            Target target = new Target();
            target.setInteractiveOnly(false);
            target.setPlatform("all");

            PushNotification notification = new PushNotification();
            notification.setMessage(message);
            notification.setTarget(target);

            HttpEntity<PushNotification> request = new HttpEntity<PushNotification>(notification, headers);
            logger.info("Sending request to {} with payload {}", options.getUri(), notification );
            ResponseEntity<String> response = restTemplate.exchange(options.getUri(), HttpMethod.POST, request, String.class);

            logger.info("exchange return code {} with body {}", response.getStatusCode(),response.getBody() );
        } catch (IOException ioe) {
            //let's try with just payload.
            logger.info("Trying with just payload, without conversion due to: " + ioe.getMessage());
            HttpHeaders headers = new HttpHeaders();

            if (options.getCredentials() != null) {
                String plainCreds = options.getCredentials();
                byte[] plainCredsBytes = plainCreds.getBytes();
                byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
                String base64Creds = new String(base64CredsBytes);

                headers.add("Authorization", "Basic " + base64Creds);
            }
            HttpEntity<String> request = new HttpEntity<String>(input, headers);
            logger.info("Sending request to {} with payload {}", options.getUri(), input );
            
            ResponseEntity<String> response = restTemplate.exchange(options.getUri(), HttpMethod.POST, request, String.class);

            logger.info("exchange return code {} with body {}", response.getStatusCode(),response.getBody() );
        }
    }
}

