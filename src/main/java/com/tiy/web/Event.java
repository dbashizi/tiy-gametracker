package com.tiy.web;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by dbashizi on 1/27/17.
 */
@Entity
@Table(name = "presenceusers")
public class Event {
    @Id
    @GeneratedValue
    private Long id;
//    private java.sql.Timestamp dateCreated = java.sql.Timestamp.valueOf(LocalDateTime.now());
    private java.sql.Timestamp dateCreated;
    private String eventName;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
