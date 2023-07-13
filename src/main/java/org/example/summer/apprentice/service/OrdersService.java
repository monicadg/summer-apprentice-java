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

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final EventRepository eventRepository;
    private final CustomerRepository customerRepository;


    @Autowired
    public OrdersService(OrdersRepository ordersRepository, EventRepository eventRepository, CustomerRepository customerRepository) {
        this.ordersRepository = ordersRepository;
        this.eventRepository = eventRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public OrdersDTO createOrder(OrdersDTO orderDTO, Long customerId) throws Exception {
        Optional<Event> event = eventRepository.findById(orderDTO.eventId());
        event.orElseThrow(() -> new Exception("no event found for eventId=" + orderDTO.eventId()));

        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.orElseThrow(() -> new Exception("no customer found for customerId=" + customerId));

        Optional<TicketCategory> ticketCategory = event.get().getTicketCategories().stream().filter(tk -> tk.getTicketCategoryId() == orderDTO.ticketCategoryId()).findFirst();
        ticketCategory.orElseThrow(() -> new Exception("no ticketCategory found for ticketCategoryId=" + orderDTO.ticketCategoryId()));

        Float price = ticketCategory.get().getPrice() * orderDTO.numberOfTickets();

        Orders order = new Orders(event.get(), customer.get(), ticketCategory.get(), orderDTO.numberOfTickets(), price);
        ordersRepository.save(order);

        return MapperUtil.transformOrdersToDTO(order);
    }

    public List<OrdersDTO> getAllOrdersForCustomer(Long customerId) throws Exception {
        Optional<List<Orders>> orders = ordersRepository.findOrdersByCustomerId(customerId);

        orders.orElseThrow(() -> new Exception("no orders found for User"));


        List<OrdersDTO> ordersDTO = new ArrayList<>();
        for (Orders order : orders.get()) {
            ordersDTO.add(MapperUtil.transformOrdersToDTO(order));
        }
        return ordersDTO;
    }


}
