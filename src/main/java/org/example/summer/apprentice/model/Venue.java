package org.example.summer.apprentice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Venue")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venueID")
    private Long venueId;
    @Column(name = "location")
    private String location;
    @Column(name = "type")
    private String type;
    @Column(name = "capacity")
    private Integer capacity;

    public Venue(Long venueId, String location, String type, Integer capacity) {
        this.venueId = venueId;
        this.location = location;
        this.type = type;
        this.capacity = capacity;
    }

    public Venue() {
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
