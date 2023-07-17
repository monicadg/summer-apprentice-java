package org.example.summer.apprentice.model;


import org.example.summer.apprentice.model.enums.TicketCategoryType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TicketCategory")
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketCategoryID")
    private Long ticketCategoryId;
    @ManyToOne
    @JoinColumn(name = "eventID", referencedColumnName = "eventID")
    private Event event;
    @Column(name = "description")
    @Enumerated(EnumType.STRING)
    private TicketCategoryType description;
    @Column(name = "price")
    private Float price;

    public TicketCategory(Long ticketCategoryId, Event event, TicketCategoryType description, Float price) {
        this.ticketCategoryId = ticketCategoryId;
        this.event = event;
        this.description = description;
        this.price = price;
    }

    public TicketCategory() {
    }

    public Long getTicketCategoryId() {
        return ticketCategoryId;
    }

    public void setTicketCategoryId(Long ticketCategoryId) {
        this.ticketCategoryId = ticketCategoryId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public TicketCategoryType getDescription() {
        return description;
    }

    public void setDescription(TicketCategoryType description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
