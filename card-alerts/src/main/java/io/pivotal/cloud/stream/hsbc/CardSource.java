package io.pivotal.cloud.stream.hsbc;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.cloud.stream.hsbc.domain.Offer;
import io.pivotal.cloud.stream.hsbc.repository.OfferRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.postgresql.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.module.PeriodicTriggerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Vinicius Carvalho
 */
@EnableBinding(Source.class)
public class CardSource {

	@Autowired
	private Source channels;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private OfferRepository offerRepository;

	private Logger logger = LoggerFactory.getLogger(CardSource.class);


	@Scheduled(fixedDelay = 5000)
	public void getCards() throws Exception{
		logger.info("Polling for new offers");
		List<Offer> offers = offerRepository.pendingOffers();
		logger.info("{} offers found",offers.size());
		for(Offer offer : offers){
			offerRepository.updateOffer(offer);
			boolean messageSent = channels.output().send(MessageBuilder.withPayload(mapper.writeValueAsString(offer)).build());
			logger.info("offer {} sent: {}", offer.getVoucherId(), String.valueOf(messageSent));
		}
	}

}
