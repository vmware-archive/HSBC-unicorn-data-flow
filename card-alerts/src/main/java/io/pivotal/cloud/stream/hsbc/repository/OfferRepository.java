package io.pivotal.cloud.stream.hsbc.repository;

import java.util.List;

import io.pivotal.cloud.stream.hsbc.domain.Offer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vinicius Carvalho
 */

@Repository
public interface OfferRepository extends CrudRepository<Offer,Long>{


	public List<Offer> findPendingOffers();
}
