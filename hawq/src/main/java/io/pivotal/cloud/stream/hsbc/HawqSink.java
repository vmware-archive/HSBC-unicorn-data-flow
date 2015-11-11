package io.pivotal.cloud.stream.hsbc;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pivotal.cloud.stream.hsbc.domain.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Vinicius Carvalho
 */
@EnableBinding(Sink.class)
public class HawqSink {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private HawqProperties properties;

	private Logger logger = LoggerFactory.getLogger(HawqSink.class);

	@ServiceActivator(inputChannel = Sink.INPUT)
	public void upadteOffer(String payload) throws Exception{
		Offer offer = mapper.readValue(payload, Offer.class);
		logger.info("Received payload: {}",payload);
		offer.setAcceptedAt(new Date());
		int rows = template.update(properties.getUpdateQuery(),offer.getVoucherId(),offer.getOfferId(),offer.getCustomerId(),offer.getAcceptedAt());
		logger.info("Database updated affected rows: {}",rows);
	}

}
