package org.example.summer.apprentice.service;

import org.example.summer.apprentice.dto.EventDTO;
import org.example.summer.apprentice.dto.mappers.EventMapper;
import org.example.summer.apprentice.model.Event;
import org.example.summer.apprentice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<EventDTO> getEvents(Long venueId, String eventTypeName) {
        final List<Event> events;
        if (Objects.isNull(venueId) && Objects.isNull(eventTypeName)) {
            events = eventRepository.findAll();
        } else {
            if (Objects.isNull(venueId)) {
                events = eventRepository.findByEventType(eventTypeName.toUpperCase())
                        .orElse(new ArrayList<>());
            } else if (Objects.isNull(eventTypeName)) {
                events = eventRepository.findByVenueId(venueId).orElse(new ArrayList<>());
            } else {
                events = eventRepository.findByVenueIdAndEventType(venueId, eventTypeName.toUpperCase())
                        .orElse(new ArrayList<>());
            }
        }

        return events.stream()
                .map(EventMapper::map)
                .toList();
    }
}
