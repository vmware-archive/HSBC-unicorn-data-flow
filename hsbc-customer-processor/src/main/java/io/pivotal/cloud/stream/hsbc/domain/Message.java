/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.pivotal.cloud.stream.hsbc.domain;

/**
 *
 * @author dpinto
 */
public class Message {
    private String body;
    private Custom custom;

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the custom
     */
    public Custom getCustom() {
        return custom;
    }

    /**
     * @param custom the custom to set
     */
    public void setCustom(Custom custom) {
        this.custom = custom;
    }
}
