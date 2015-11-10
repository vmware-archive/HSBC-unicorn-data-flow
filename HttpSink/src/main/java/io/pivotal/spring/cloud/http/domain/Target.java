/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.pivotal.spring.cloud.http.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author dpinto
 */
public class Target {
    private String[] tags;
    private String[] platforms;
    private String[] devices;
    @JsonProperty("interactive-only")
    private boolean interactiveOnly;
    private String platform="all";

    /**
     * @return the tags
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    /**
     * @return the platforms
     */
    public String[] getPlatforms() {
        return platforms;
    }

    /**
     * @param platforms the platforms to set
     */
    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    /**
     * @return the devices
     */
    public String[] getDevices() {
        return devices;
    }

    /**
     * @param devices the devices to set
     */
    public void setDevices(String[] devices) {
        this.devices = devices;
    }

    /**
     * @return the interactiveOnly
     */
    public boolean isInteractiveOnly() {
        return interactiveOnly;
    }

    /**
     * @param interactiveOnly the interactiveOnly to set
     */
    public void setInteractiveOnly(boolean interactiveOnly) {
        this.interactiveOnly = interactiveOnly;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
