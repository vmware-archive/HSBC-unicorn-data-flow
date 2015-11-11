package io.pivotal.cloud.stream.hsbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import io.pivotal.cloud.stream.hsbc.CardAlertsProperties;
import io.pivotal.cloud.stream.hsbc.domain.Offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


/**
 * @author Vinicius Carvalho
 */
@Component
public class OfferRepository {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private CardAlertsProperties properties;

	public List<Offer> pendingOffers() {
		List<Offer> offers = template.query(properties.getLookupQuery(),new OfferMapper());
		return offers;
	}

	public void updateOffer(Offer offer){
		offer.setIssuedAt(new Date());
		template.update(properties.getUpdateQuery(),offer.getVoucherId(),offer.getOfferId(),offer.getCustomerId(),offer.getIssuedAt());
	}


	static class OfferMapper implements RowMapper<Offer>{

		@Override
		public Offer mapRow(ResultSet resultSet, int i) throws SQLException {
			Offer offer = new Offer();

			offer.setCustomerId(resultSet.getLong("customerid"));
			offer.setVoucherId(resultSet.getLong("voucherid"));
			offer.setOfferId(resultSet.getLong("offerid"));
			return offer;
		}
	}
}
