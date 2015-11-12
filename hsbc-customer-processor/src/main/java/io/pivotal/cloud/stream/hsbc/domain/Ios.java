/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.pivotal.cloud.stream.hsbc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dpinto
 */
public class Ios {
    private Map<String,String> alert = new HashMap<>();
    private String category;
    private int badge;
    private String sound;
    @JsonProperty("content-available")
    private boolean contentAvailable;

    /**
     * @return the alert
     */
    public Map<String,String> getAlert() {
        return alert;
    }

    /**
     * @param alert the alert to set
     */
    public void setAlert(Map<String,String> alert) {
        this.alert = alert;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the badge
     */
    public int getBadge() {
        return badge;
    }

    /**
     * @param badge the badge to set
     */
    public void setBadge(int badge) {
        this.badge = badge;
    }

    /**
     * @return the sound
     */
    public String getSound() {
        return sound;
    }

    /**
     * @param sound the sound to set
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     * @return the contentAvailable
     */
    public boolean isContentAvailable() {
        return contentAvailable;
    }

    /**
     * @param contentAvailable the contentAvailable to set
     */
    public void setContentAvailable(boolean contentAvailable) {
        this.contentAvailable = contentAvailable;
    }
}
