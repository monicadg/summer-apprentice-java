package org.example.summer.apprentice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "Orders") //TODO replace with "Order"
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private Long ordersId;
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ticketCategoryID", referencedColumnName = "ticketCategoryID")
    private TicketCategory ticketCategory;
    @Column(name = "orderedAt")
    private LocalDateTime orderedAt = LocalDateTime.now();
    @Column(name = "numberOfTickets")
    private Integer numberOfTickets;
    @Column(name = "totalPrice")
    private Float totalPrice;

    public Order(Long ordersId, User user, TicketCategory ticketCategory, Integer numberOfTickets, Float totalPrice) {
        this.ordersId = ordersId;
        this.user = user;
        this.ticketCategory = ticketCategory;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
    }

    public Order(User user, TicketCategory ticketCategory, Integer numberOfTickets, Float totalPrice) {
        this.user = user;
        this.ticketCategory = ticketCategory;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
    }

    public Order(Long ordersId, User user, TicketCategory ticketCategory, LocalDateTime orderedAt, Integer numberOfTickets, Float totalPrice) {
        this.ordersId = ordersId;
        this.user = user;
        this.ticketCategory = ticketCategory;
        this.orderedAt = orderedAt;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public User getCustomer() {
        return user;
    }

    public void setCustomer(User user) {
        this.user = user;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategoryId(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return ordersId.equals(order.ordersId) && Objects.equals(user, order.user) && Objects.equals(ticketCategory, order.ticketCategory) &&
                Objects.equals(orderedAt, order.orderedAt) && Objects.equals(numberOfTickets, order.numberOfTickets) &&
                Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordersId, user, ticketCategory, orderedAt, numberOfTickets, totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "ordersId=" + ordersId +
                ", user=" + user +
                ", ticketCategory=" + ticketCategory +
                ", timestamp=" + orderedAt +
                ", numberOfTickets=" + numberOfTickets +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
