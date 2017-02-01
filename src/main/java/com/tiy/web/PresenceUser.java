package com.tiy.web;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dbashizi on 1/26/17.
 */
@Entity
@Table(name = "presenceusers")
public class PresenceUser {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String location;
    private String position;


    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy="requester")
    @JsonManagedReference
    private Set<ContactRequest> requestsMade;
    @OneToMany(fetch = FetchType.EAGER,orphanRemoval = true, mappedBy="requestee")
    @JsonManagedReference
    private Set<ContactRequest> requestsReceived;

    public PresenceUser() {
    }

    public Set<ContactRequest> getRequestsMade() {
        return requestsMade;
    }

    public void setRequestsMade(Set<ContactRequest> requestsMade) {
        this.requestsMade = requestsMade;
    }

    public Set<ContactRequest> getRequestsReceived() {
        return requestsReceived;
    }

    public void setRequestsReceived(Set<ContactRequest> requestsReceived) {
        this.requestsReceived = requestsReceived;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
