/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.pivotal.spring.cloud.http.domain;

/**
 *
 * @author dpinto
 */
public class PushNotification {

    private Message message;
    private Target target;
    private Long scheduleAt;
    private Integer scheduleIn;
    private Long expiryTime;

    /**
     * @return the message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * @return the target
     */
    public Target getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Target target) {
        this.target = target;
    }

    /**
     * @return the scheduleAt
     */
    public Long getScheduleAt() {
        return scheduleAt;
    }

    /**
     * @param scheduleAt the scheduleAt to set
     */
    public void setScheduleAt(Long scheduleAt) {
        this.scheduleAt = scheduleAt;
    }

    /**
     * @return the scheduleIn
     */
    public Integer getScheduleIn() {
        return scheduleIn;
    }

    /**
     * @param scheduleIn the scheduleIn to set
     */
    public void setScheduleIn(Integer scheduleIn) {
        this.scheduleIn = scheduleIn;
    }

    /**
     * @return the expiryTime
     */
    public Long getExpiryTime() {
        return expiryTime;
    }

    /**
     * @param expiryTime the expiryTime to set
     */
    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }
}
