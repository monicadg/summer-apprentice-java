package org.example.summer.apprentice.controller;

import org.example.summer.apprentice.dto.OrderDTO;
import org.example.summer.apprentice.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

/**
 * Rest Controller for Orders
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * Get All Orders for user
     *
     * @return ResponseEntity<List < OrdersDTO>>
     */
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() {
        List<OrderDTO> orders = ordersService.getAllOrdersForUser(1L);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Create Order for user
     *
     * @param orderDTO
     * @return ResponseEntity<OrdersDTO>
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        if (Objects.isNull(orderDTO.eventId()) || Objects.isNull(orderDTO.ticketCategoryId()) || Objects.isNull(orderDTO.numberOfTickets())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body");
        }

        OrderDTO order = ordersService.createOrder(orderDTO, 1L);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
