package org.example.summer.apprentice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EventType")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventTypeID")
    private Long eventTypeId;
    @Column(name = "name")
    private String eventTypeName;

    public EventType(Long eventTypeId, String eventTypeName) {
        this.eventTypeId = eventTypeId;
        this.eventTypeName = eventTypeName;
    }

    public EventType() {
    }

    public Long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Long eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

}