package org.example.summer.apprentice.service;

import org.example.summer.apprentice.dto.OrdersDTO;
import org.example.summer.apprentice.dto.mappers.MapperUtil;
import org.example.summer.apprentice.model.*;
import org.example.summer.apprentice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  Service for Orders
 */
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    @Autowired
    public OrdersService(OrdersRepository ordersRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.ordersRepository = ordersRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates and order of a given userId
     * @param orderDTO
     * @param userId
     * @return OrdersDTO
     * @throws Exception
     */
    @Transactional
    public OrdersDTO createOrder(OrdersDTO orderDTO, Long userId) throws Exception {
        Optional<Event> event = eventRepository.findById(orderDTO.eventId());
        event.orElseThrow(() -> new Exception("no event found for eventId=" + orderDTO.eventId()));

        Optional<User> user = userRepository.findById(userId);
        user.orElseThrow(() -> new Exception("no user found for userId=" + userId));

        Optional<TicketCategory> ticketCategory = event.get().getTicketCategories().stream().filter(tk -> tk.getTicketCategoryId() == orderDTO.ticketCategoryId()).findFirst();
        ticketCategory.orElseThrow(() -> new Exception("no ticketCategory found for ticketCategoryId=" + orderDTO.ticketCategoryId()));

        Float price = ticketCategory.get().getPrice() * orderDTO.numberOfTickets();

        Orders order = new Orders(user.get(), ticketCategory.get(), orderDTO.numberOfTickets(), price);
        ordersRepository.save(order);

        return MapperUtil.transformOrdersToDTO(order);
    }

    /**
     * Returns all placed orders for a given user
     * @param userId
     * @return List<OrdersDTO>
     * @throws Exception
     */
    public List<OrdersDTO> getAllOrdersForUser(Long userId) throws Exception {
        Optional<List<Orders>> orders = ordersRepository.findOrdersByUserId(userId);

        orders.orElseThrow(() -> new Exception("no orders found for User"));

        List<OrdersDTO> ordersDTO = new ArrayList<>();
        for (Orders order : orders.get()) {
            ordersDTO.add(MapperUtil.transformOrdersToDTO(order));
        }
        return ordersDTO;
    }


}
