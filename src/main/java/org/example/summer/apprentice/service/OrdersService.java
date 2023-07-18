package org.example.summer.apprentice.service;

import org.example.summer.apprentice.dto.OrderDTO;
import org.example.summer.apprentice.dto.mappers.OrderMapper;
import org.example.summer.apprentice.model.Event;
import org.example.summer.apprentice.model.Order;
import org.example.summer.apprentice.model.TicketCategory;
import org.example.summer.apprentice.model.User;
import org.example.summer.apprentice.repository.EventRepository;
import org.example.summer.apprentice.repository.OrderRepository;
import org.example.summer.apprentice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for Orders
 */
@Service
public class OrdersService {

    private final OrderRepository orderRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    @Autowired
    public OrdersService(OrderRepository orderRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates and order of a given userId
     *
     * @param orderDTO
     * @param userId
     * @return OrdersDTO
     * @throws Exception
     */
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO, Long userId) throws Exception {
        Event event = eventRepository.findById(orderDTO.eventId())
                .orElseThrow(() -> new Exception("no event found for eventId=" + orderDTO.eventId()));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("no user found for userId=" + userId));

        TicketCategory ticketCategory = event.getTicketCategories().stream()
                .filter(tk -> tk.getTicketCategoryId().equals(orderDTO.ticketCategoryId()))
                .findFirst()
                .orElseThrow(() -> new Exception("no ticketCategory found for ticketCategoryId=" + orderDTO.ticketCategoryId()));

        Float price = ticketCategory.getPrice() * orderDTO.numberOfTickets();

        Order order = new Order(user, ticketCategory, orderDTO.numberOfTickets(), price);
        orderRepository.save(order);

        return OrderMapper.map(order);
    }

    /**
     * Returns all placed orders for a given user
     *
     * @param userId
     * @return List<OrdersDTO>
     */
    public List<OrderDTO> getOrders(Long userId) {
        List<Order> orders = orderRepository.findOrdersByUserId(userId)
                .orElse(new ArrayList<>());

        return orders.stream()
                .map(OrderMapper::map)
                .toList();
    }
}
