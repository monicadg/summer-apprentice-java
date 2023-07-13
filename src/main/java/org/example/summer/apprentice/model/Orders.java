package org.example.summer.apprentice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "orders")
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long ordersId;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "ticket_category_id")
    private TicketCategory  ticketCategory;
    @Column(name = "timestamp")
    private LocalDateTime timestamp = LocalDateTime.now();
    @Column(name = "number_of_tickets")
    private Integer numberOfTickets ;
    @Column(name = "total_price")
    private Float totalPrice;

    public Orders(Long ordersId, Event event, Customer customer, TicketCategory ticketCategory, Integer numberOfTickets, Float totalPrice) {
        this.ordersId = ordersId;
        this.event = event;
        this.customer = customer;
        this.ticketCategory = ticketCategory;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
    }

    public Orders(Long ordersId, Event event, Customer customer, TicketCategory ticketCategory,LocalDateTime timestamp, Integer numberOfTickets, Float totalPrice) {
        this.ordersId = ordersId;
        this.event = event;
        this.customer = customer;
        this.ticketCategory = ticketCategory;
        this.timestamp = timestamp;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
    }

    public Orders(){}

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategoryId(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return ordersId.equals(orders.ordersId) && Objects.equals(event, orders.event) && Objects.equals(customer, orders.customer) && Objects.equals(ticketCategory, orders.ticketCategory) && Objects.equals(timestamp, orders.timestamp) && Objects.equals(numberOfTickets, orders.numberOfTickets) && Objects.equals(totalPrice, orders.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordersId, event, customer, ticketCategory, timestamp, numberOfTickets, totalPrice);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", event=" + event +
                ", customer=" + customer +
                ", ticketCategory=" + ticketCategory +
                ", timestamp=" + timestamp +
                ", numberOfTickets=" + numberOfTickets +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
