package com.redhat.quarkus.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccessLog {
    private String recordId;
    private Long personId;
    private LocalDateTime entryTime;
    private String destination;

    public AccessLog(Long personId, String destination) {
        this.recordId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        this.personId = personId;
        this.entryTime = LocalDateTime.now();
        this.destination = destination;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "AccessLog{" +
                "recordId=" + recordId +
                ", personId=" + personId +
                ", entryTime='" + entryTime + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
