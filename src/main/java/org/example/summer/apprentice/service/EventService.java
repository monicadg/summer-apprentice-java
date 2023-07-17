package org.example.summer.apprentice.service;

import org.example.summer.apprentice.dto.EventDTO;
import org.example.summer.apprentice.dto.mappers.EventMapper;
import org.example.summer.apprentice.model.Event;
import org.example.summer.apprentice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for Events
 */
@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Finds all events that correspond to venueId and eventTypeName
     *
     * @param venueId
     * @param eventTypeName
     * @return Event
     */
    public List<EventDTO> findEventsByVenueIdAndEventType(long venueId, String eventTypeName) {
        List<Event> events = eventRepository.findByVenueIdAndEventType(venueId, eventTypeName)
                .orElse(new ArrayList<>());

        return events.stream()
                .map(EventMapper::map)
                .toList();
    }
}
