package io.pivotal.cloud.stream.hsbc;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Vinicius Carvalho
 */
@ConfigurationProperties
public class CardAlertsProperties {

	private String lookupQuery;
	private String updateQuery;
	private Integer interval;

	public String getLookupQuery() {
		return lookupQuery;
	}

	public void setLookupQuery(String lookupQuery) {
		this.lookupQuery = lookupQuery;
	}

	public String getUpdateQuery() {
		return updateQuery;
	}

	public void setUpdateQuery(String updateQuery) {
		this.updateQuery = updateQuery;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}
}
