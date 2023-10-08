package com.redhat.quarkus.model;

public class AccessLog {
    private Long recordId;
    private Long personId;
    private String entryTime;
    private String destination;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
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
