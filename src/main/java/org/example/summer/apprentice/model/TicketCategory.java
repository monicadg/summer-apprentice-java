package org.example.summer.apprentice.model;


import org.example.summer.apprentice.model.enums.TicketCategoryType;

import javax.persistence.*;

@Entity
@Table(name = "ticketcategory")
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_category_id")
    private Long ticketCategoryId;
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
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

    public TicketCategory(){}

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
