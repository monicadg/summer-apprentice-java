package org.example.summer.apprentice.dto.mappers;

import org.example.summer.apprentice.dto.OrderDTO;
import org.example.summer.apprentice.model.Order;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderDTO map(Order order) {
        return new OrderDTO(order.getTicketCategory().getEvent().getEventId(),
                order.getOrderedAt(),
                order.getTicketCategory().getTicketCategoryId(),
                order.getNumberOfTickets(), order.getTotalPrice());
    }
}
