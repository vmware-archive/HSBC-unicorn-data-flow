/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.pivotal.spring.cloud.http;

import javax.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author dpinto
 */
@ConfigurationProperties
public class HttpSinkOptionsMetadata {
    private String credentials;
    private String uri;
    private String method = "POST";

    /**
     * @return the credentials
     */
    public String getCredentials() {
        return credentials;
    }

    /**
     * @param credentials the credentials to set
     */
    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the method
     */
    @Pattern(regexp = "(?i)(POST|GET|)",
			message = "method must be one of POST, GET (case-insensitive)")
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }
}
