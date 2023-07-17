package org.example.summer.apprentice.dto.mappers;

import org.example.summer.apprentice.dto.EventDTO;
import org.example.summer.apprentice.dto.TicketCategoryDTO;
import org.example.summer.apprentice.dto.VenueDTO;
import org.example.summer.apprentice.model.Event;
import org.example.summer.apprentice.model.EventType;
import org.example.summer.apprentice.model.TicketCategory;
import org.example.summer.apprentice.model.Venue;

import java.util.ArrayList;
import java.util.List;

public class EventMapper {

    private EventMapper() {
    }

    public static EventDTO map(Event event) {
        Venue venue = event.getVenue();
        EventType eventType = event.getEventType();

        VenueDTO venueDTO = new VenueDTO(venue.getVenueId(), venue.getType(), venue.getLocation(), venue.getCapacity());
        List<TicketCategoryDTO> ticketCategoryDTOList = new ArrayList<>();
        for (TicketCategory ticketCategory : event.getTicketCategories()) {
            ticketCategoryDTOList.add(new TicketCategoryDTO(ticketCategory.getTicketCategoryId(), ticketCategory.getDescription(), ticketCategory.getPrice()));
        }

        return new EventDTO(event.getEventId(), venueDTO,
                eventType.getEventTypeName(), event.getDescription(),
                event.getName(), event.getStartDate(),
                event.getEndDate(), ticketCategoryDTOList);
    }
}
