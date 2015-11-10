package io.pivotal.cloud.stream.hsbc;

import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.cloud.stream.hsbc.domain.Offer;
import io.pivotal.cloud.stream.hsbc.repository.OfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.module.PeriodicTriggerConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

/**
 * @author Vinicius Carvalho
 */
@EnableBinding(Source.class)
@EnableConfigurationProperties(CardAlertsProperties.class)
@Import(PeriodicTriggerConfiguration.class)
public class CardSource {

	@Autowired
	private Source channels;

	@Autowired
	private ObjectMapper mapper;

	Random random = new Random();

	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(
			trigger = PeriodicTriggerConfiguration.TRIGGER_BEAN_NAME, maxMessagesPerPoll = "1"))
	public String getCards() throws Exception{
		Offer offer = new Offer();
		offer.setOfferId(random.nextLong());
		offer.setVoucherId(random.nextLong());
		offer.setCustomerId(random.nextLong());
		offer.setIssuedAt(System.currentTimeMillis());
		return mapper.writeValueAsString(offer);
	}
}
