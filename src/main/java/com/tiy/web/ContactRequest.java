package com.tiy.web;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by dbashizi on 1/26/17.
 */
@Entity
@Table(name = "contactrequests")
public class ContactRequest {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JsonBackReference
    private PresenceUser requester;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JsonBackReference
    private PresenceUser requestee;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PresenceUser getRequester() {
        return requester;
    }

    public void setRequester(PresenceUser requester) {
        this.requester = requester;
    }

    public PresenceUser getRequestee() {
        return requestee;
    }

    public void setRequestee(PresenceUser requestee) {
        this.requestee = requestee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContactRequest() {
    }

}
