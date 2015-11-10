package io.pivotal.cloud.stream.hsbc.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vinicius Carvalho
 */
@Entity
@Table(name = "OFFER_VOUCHERS")
public class Offer {

	@Id
	private Long voucherId;
	private Long offerId;
	private Long customerId;

	private Long issuedAt;
	private Long expiresAt;
	private Long acceptedAt;


	public Long getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Long issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Long getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Long getAcceptedAt() {
		return acceptedAt;
	}

	public void setAcceptedAt(Long acceptedAt) {
		this.acceptedAt = acceptedAt;
	}
}
