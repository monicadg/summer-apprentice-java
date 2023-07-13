package org.example.summer.apprentice.controller;

import org.example.summer.apprentice.dto.OrdersDTO;
import org.example.summer.apprentice.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService){
        this.ordersService = ordersService;
    }

    @GetMapping("/orders")

    public ResponseEntity<List<OrdersDTO>> getOrdersByVenueIdAndEventType(@RequestParam("venueId") Long venueId, @RequestParam("eventType") String eventType) throws Exception{
        List<OrdersDTO> orders = ordersService.findOrdersByVenueIdAndEventType(venueId,eventType);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
