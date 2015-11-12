package io.pivotal.cloud.stream.hsbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.cloud.stream.hsbc.domain.Message;
import io.pivotal.cloud.stream.hsbc.domain.Offer;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.cloud.stream.annotation.Bindings;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Vinicius Carvalho
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CustomerProcessorApplication.class)
@DirtiesContext
@WebIntegrationTest({"server.port:0","credentials=a:b","endpoint=http://localhost"})
public class CustomerProcessorTest {

	@Autowired
	@Bindings(CustomerProcessor.class)
	private Processor processor;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	protected MessageCollector collector;

	@Test
	public void processMessage() throws Exception{
		Offer offer = new Offer();
		offer.setOfferId(1L);
		offer.setVoucherId(1L);
		offer.setCustomerId(1L);
		processor.input().send(MessageBuilder.withPayload(mapper.writeValueAsString(offer)).build());

		Thread.sleep(5000L);
	}

}
