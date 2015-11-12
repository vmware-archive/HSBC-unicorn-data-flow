package io.pivotal.cloud.stream.hsbc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.cloud.stream.hsbc.domain.Offer;
import io.pivotal.cloud.stream.hsbc.domain.PushNotification;
import io.pivotal.cloud.stream.hsbc.domain.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

/**
 * @author Vinicius Carvalho
 */
@EnableBinding(Processor.class)
public class CustomerProcessor {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private Processor channels;

	@Autowired
	private CustomerProcessorProperties properties;

	Logger logger = LoggerFactory.getLogger(CustomerProcessor.class);

	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Message process(Message input) throws Exception{
		logger.info("Received message {}", input.getPayload().toString());
		Offer offer = mapper.readValue(input.getPayload().toString(),Offer.class);
		PushNotification pushNotification = new PushNotification();
		io.pivotal.cloud.stream.hsbc.domain.Message m = new io.pivotal.cloud.stream.hsbc.domain.Message();
		m.setBody(input.getPayload().toString());
		pushNotification.setMessage(m);
		pushNotification.setTarget(getCustomerTarget(offer));
		Map<String,Object> headers = new HashMap<>();
		headers.put("ENDPOINT",properties.getEndpoint());
		headers.put("METHOD","POST");
		Map<String,String> httpHeaders = new HashMap<>();
		httpHeaders.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		httpHeaders.put(HttpHeaders.AUTHORIZATION,String.format("Basic %s", Base64Utils.encodeToString(properties.getCredentials().getBytes())));
		headers.put("HTTP_HEADERS",httpHeaders);
		String payload = mapper.writeValueAsString(pushNotification);
		logger.info("Publishing message {} with headers {} to endpoint {}", payload, httpHeaders, properties.getEndpoint());

		return MessageBuilder.createMessage(payload,new MessageHeaders(headers));

	}


	private Target getCustomerTarget(Offer offer){
		Target target = null;
		if(offer.getCustomerId() != 32767){
			target = new Target();
			target.setDevices(new String[]{UUID.randomUUID().toString()});
		}
		return target;
	}

}
