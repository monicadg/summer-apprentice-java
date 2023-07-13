package org.example.summer.apprentice.dto.mappers;

import org.example.summer.apprentice.dto.OrdersDTO;
import org.example.summer.apprentice.dto.EventDTO;
import org.example.summer.apprentice.dto.TicketCategoryDTO;
import org.example.summer.apprentice.dto.VenueDTO;
import org.example.summer.apprentice.model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MapperUtil {

    public static OrdersDTO transformOrdersToDTO(Orders order) {
        return new OrdersDTO(order.getEvent().getEventId(), order.getTimestamp(), order.getTicketCategory().getTicketCategoryId(), order.getNumberOfTickets(), order.getTotalPrice());
    }

    public static EventDTO transformEventToDTO(Event event) {
        Venue venue = event.getVenue();
        EventType eventType = event.getEventType();

        VenueDTO venueDTO = new VenueDTO(venue.getVenueId(), venue.getType(), venue.getLocation(), venue.getCapacity());
        List<TicketCategoryDTO> ticketCategoryDTOList = new ArrayList<>();
        for (TicketCategory ticketCategory : event.getTicketCategories()) {
            ticketCategoryDTOList.add(new TicketCategoryDTO(ticketCategory.getTicketCategoryId(), ticketCategory.getDescription(), ticketCategory.getPrice()));
        }

        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String startDate = simpleDateFormat.format(event.getStartDate());
        String endDate = simpleDateFormat.format(event.getEndDate());

        return new EventDTO(event.getEventId(), venueDTO, eventType.getEventTypeName(), event.getEventDescription(), event.getEventName(), startDate, endDate, ticketCategoryDTOList);
    }
}
