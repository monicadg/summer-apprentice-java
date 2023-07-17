package org.example.summer.apprentice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventID")
    private Long eventId;
    @ManyToOne
    @JoinColumn(name = "venueID", referencedColumnName = "venueID")
    private Venue venue;
    @ManyToOne
    @JoinColumn(name = "eventTypeID")
    private EventType eventType;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;

    @OneToMany(mappedBy = "event")
    private List<TicketCategory> ticketCategories = new ArrayList<>();
    ;

    public Event(Long eventId, Venue venue, EventType eventType, String description, String name, LocalDate startDate, LocalDate endDate,
                 List<TicketCategory> ticketCategories) {
        this.eventId = eventId;
        this.venue = venue;
        this.eventType = eventType;
        this.description = description;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketCategories = ticketCategories;
    }

    public Event(Long eventId, Venue venue, EventType eventType, String description, String name, LocalDate startDate, LocalDate endDate) {
        this.eventId = eventId;
        this.venue = venue;
        this.eventType = eventType;
        this.description = description;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event() {
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getEventId() {
        return eventId;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<TicketCategory> getTicketCategories() {
        return ticketCategories;
    }

    public void setTicketCategories(List<TicketCategory> ticketCategories) {
        this.ticketCategories = ticketCategories;
    }
}
