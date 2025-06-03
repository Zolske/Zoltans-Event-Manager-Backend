package com.kepes.dto;

public class CreateSubscriptionRequest {
    private String userId;
    private Long eventId;

    public String getUserId() {
        return userId;
    }

    public Long getEventId() {
        return eventId;
    }
}
