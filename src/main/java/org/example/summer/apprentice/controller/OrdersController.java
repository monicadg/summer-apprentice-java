package org.example.summer.apprentice.controller;

import org.example.summer.apprentice.dto.OrdersDTO;
import org.example.summer.apprentice.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getOrders() throws Exception {
        List<OrdersDTO> orders = ordersService.getAllOrdersForUser(1l);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrdersDTO> createOrder(@RequestBody OrdersDTO ordersDTO) throws Exception {
        if (ordersDTO.eventId() == null || ordersDTO.ticketCategoryId() == null || ordersDTO.numberOfTickets() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid request body");
        }

        OrdersDTO order = ordersService.createOrder(ordersDTO, 1l);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
