package org.example.summer.apprentice.service;


import org.example.summer.apprentice.dto.OrderDTO;
import org.example.summer.apprentice.model.*;
import org.example.summer.apprentice.model.enums.TicketCategoryType;
import org.example.summer.apprentice.repository.EventRepository;
import org.example.summer.apprentice.repository.OrderRepository;
import org.example.summer.apprentice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private static final Long USER_ID = 1L;
    private static final Long EVENT_ID = 1L;
    private static final Long TICKET_CATEGORY_ID = 1L;
    private static final Long ORDERS_ID = 1L;
    @Mock
    EventRepository eventRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrdersService ordersService;

    Event event;
    TicketCategory ticketCategory;
    User user;

    @BeforeEach
    void setup() {
        event = new Event(EVENT_ID, new Venue(), new EventType(), "Festival", "Untold", LocalDate.now(), LocalDate.now().plusDays(3));
        ticketCategory = new TicketCategory(TICKET_CATEGORY_ID, event, TicketCategoryType.STANDARD, 600f);
        event.setTicketCategories(List.of(ticketCategory));

        user = new User("John Doe", "john.doe@email.com");
    }

    @Test
    void getOrdersSuccess() throws Exception {
        Order order = new Order(ORDERS_ID, user, ticketCategory, 2, 1600f);
        given(orderRepository.findOrdersByUserId(USER_ID)).willReturn(Optional.of(List.of(order)));

        List<OrderDTO> resultOrderDTOList = ordersService.getOrders(USER_ID);
        assertThat(resultOrderDTOList.get(0).timestamp().toLocalDate()).isEqualTo(LocalDateTime.now().toLocalDate());
        assertThat(resultOrderDTOList.get(0).totalPrice()).isEqualTo(1600f);
    }

    @Test
    void createOrderSuccess() throws Exception {
        when(eventRepository.findById(EVENT_ID)).thenReturn(Optional.of(event));
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        OrderDTO orderDTO = new OrderDTO(EVENT_ID, null, TICKET_CATEGORY_ID, 3, null);
        OrderDTO resultOrderDTO = ordersService.createOrder(orderDTO, USER_ID);
        assertThat(resultOrderDTO.timestamp().toLocalDate()).isEqualTo(LocalDateTime.now().toLocalDate());
        assertThat(resultOrderDTO.totalPrice()).isEqualTo(1800);
    }

    @Test
    void createOrderThrowException() {
        OrderDTO orderDTO = new OrderDTO(null, null, TICKET_CATEGORY_ID, 3, null);

        assertThatThrownBy(() -> ordersService.createOrder(orderDTO, USER_ID))
                .isInstanceOf(Exception.class)
                .hasMessage("no event found for eventId=" + orderDTO.eventId());
    }
}
