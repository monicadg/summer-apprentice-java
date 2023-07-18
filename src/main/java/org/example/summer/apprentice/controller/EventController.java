package org.example.summer.apprentice.controller;

import org.example.summer.apprentice.dto.EventDTO;
import org.example.summer.apprentice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller for Events
 */
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Get all Events that have the specified venueId and eventType
     *
     * @param venueId
     * @param eventType
     * @return ResponseEntity<List < EventDTO>>
     */
    @GetMapping
    public ResponseEntity<List<EventDTO>> getEvents(@RequestParam(value = "venueId", required = false) Long venueId,
                                                    @RequestParam(value = "eventType", required = false) String eventType) {
        List<EventDTO> orders = eventService.getEvents(venueId, eventType);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
