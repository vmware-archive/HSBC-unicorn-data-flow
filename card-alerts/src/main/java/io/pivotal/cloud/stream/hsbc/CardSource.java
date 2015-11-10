package io.pivotal.cloud.stream.hsbc;

import io.pivotal.cloud.stream.hsbc.repository.OfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author Vinicius Carvalho
 */
@EnableBinding(Source.class)
@EnableConfigurationProperties(CardAlertsProperties.class)
public class CardSource {

	@Autowired
	private Source channels;

	@Autowired
	private OfferRepository offerRepository;


}
