package org.example.summer.apprentice.service;

import org.example.summer.apprentice.dto.OrdersDTO;
import org.example.summer.apprentice.dto.TicketCategoryDTO;
import org.example.summer.apprentice.dto.VenueDTO;
import org.example.summer.apprentice.model.*;
import org.example.summer.apprentice.repository.OrdersRepository;
import org.example.summer.apprentice.repository.TicketCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class OrdersService {

    private final OrdersRepository ordersRepository;


    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<OrdersDTO> findOrdersByVenueIdAndEventType(long venueId, String eventTypeName) throws Exception {

        Optional<List<Orders>> orders = ordersRepository.findOrdersByVenueIdAndEventType(venueId, eventTypeName);

        orders.orElseThrow(() -> new Exception("no orders found"));

        List<OrdersDTO> ordersDTO = new ArrayList<>();
        for (Orders order : orders.get()) {
            ordersDTO.add(transformOrderToDTO(order));
        }

        return ordersDTO;
    }

    OrdersDTO transformOrderToDTO(Orders order) {
        Event event = order.getEvent();
        Venue venue = event.getVenue();
        EventType eventType = event.getEventType();

        VenueDTO venueDTO = new VenueDTO(venue.getVenueId(), venue.getType(), venue.getLocation(), venue.getCapacity());
        List<TicketCategoryDTO> ticketCategoryDTOList = new ArrayList<>();
        for (TicketCategory ticketCategory : event.getTicketCategories()) {
            ticketCategoryDTOList.add(new TicketCategoryDTO(ticketCategory.getTicketCategoryId(), ticketCategory.getDescription(), ticketCategory.getPrice()));
        }
        OrdersDTO ordersDTO = new OrdersDTO(order.getOrdersId(), venueDTO, eventType.getEventTypeName(), event.getEventDescription(), event.getEventName(), event.getStartDate(), event.getEndDate(), ticketCategoryDTOList);
        return ordersDTO;
    }
}
