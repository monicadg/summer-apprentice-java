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

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping(value = "", params = {"venueId", "eventType"})
    public ResponseEntity<List<EventDTO>> getOrdersByVenueIdAndEventType(@RequestParam("venueId") Long venueId, @RequestParam("eventType") String eventType) throws Exception {
        List<EventDTO> orders = eventService.findEventsByVenueIdAndEventType(venueId, eventType);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
