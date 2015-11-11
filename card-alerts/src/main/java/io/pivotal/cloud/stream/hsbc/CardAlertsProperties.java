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
	private String url;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

}
