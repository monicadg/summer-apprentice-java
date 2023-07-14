package org.example.summer.apprentice.service;

import org.example.summer.apprentice.dto.EventDTO;
import org.example.summer.apprentice.dto.mappers.MapperUtil;
import org.example.summer.apprentice.model.Event;
import org.example.summer.apprentice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for Events
 */
@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    /**
     * Finds all events that correspond to venueId and eventTypeName
     * @param venueId
     * @param eventTypeName
     * @return Event
     * @throws Exception
     */
    public List<EventDTO> findEventsByVenueIdAndEventType(long venueId, String eventTypeName) throws Exception {
        Optional<List<Event>> events = eventRepository.findByVenueIdAndEventType(venueId, eventTypeName);

        events.orElseThrow(() -> new Exception("no orders found"));

        List<EventDTO> ordersDTO = new ArrayList<>();
        for (Event event : events.get()) {
            ordersDTO.add(MapperUtil.transformEventToDTO(event));
        }

        return ordersDTO;
    }
}
