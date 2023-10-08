package com.redhat.quarkus.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExitLog {
    private String recordId;
    private Long personId;
    private LocalDateTime exitTime;
    private String destination;

    public ExitLog(Long personId, String destination) {
        this.recordId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        this.personId = personId;
        this.exitTime = LocalDateTime.now();
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

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "ExitLog{" +
                "recordId=" + recordId +
                ", personId=" + personId +
                ", exitTime='" + exitTime + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
