package org.example.summer.apprentice.service;


import org.example.summer.apprentice.dto.OrdersDTO;
import org.example.summer.apprentice.model.*;
import org.example.summer.apprentice.model.enums.TicketCategoryType;
import org.example.summer.apprentice.repository.EventRepository;
import org.example.summer.apprentice.repository.OrdersRepository;
import org.example.summer.apprentice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {

    private static final Long USER_ID = 1l;
    private static final Long EVENT_ID = 1l;
    private static final Long TICKET_CATEGORY_ID = 1l;
    private static final Long ORDERS_ID = 1l;
    @Mock
    EventRepository eventRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    OrdersRepository ordersRepository;
    @InjectMocks
    OrdersService ordersService;

    Event event;
    TicketCategory ticketCategory;
    User user;

    @BeforeEach
    void setup() {
        event = new Event(EVENT_ID, new Venue(), new EventType(), "Festival", "Untold", new Date(1691010000000l), new Date(1691355600000l));
        ticketCategory = new TicketCategory(TICKET_CATEGORY_ID, event, TicketCategoryType.Standard, 600f);
        event.setTicketCategories(List.of(ticketCategory));

        user = new User("John Doe", "john.doe@email.com");
    }

    @Test
    void getOrdersSuccess() throws Exception {
        Orders order = new Orders(ORDERS_ID, user, ticketCategory, 2, 1600f);
        given(ordersRepository.findOrdersByUserId(USER_ID)).willReturn(Optional.of(List.of(order)));

        List<OrdersDTO> resultOrdersDTOList = ordersService.getAllOrdersForUser(USER_ID);
        assertThat(resultOrdersDTOList.get(0).timestamp().toLocalDate()).isEqualTo(LocalDateTime.now().toLocalDate());
        assertThat(resultOrdersDTOList.get(0).totalPrice()).isEqualTo(1600f);
    }

    @Test
    void createOrderSuccess() throws Exception {
        when(eventRepository.findById(EVENT_ID)).thenReturn(Optional.of(event));
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        OrdersDTO ordersDTO = new OrdersDTO(EVENT_ID, null, TICKET_CATEGORY_ID, 3, null);
        OrdersDTO resultOrdersDTO = ordersService.createOrder(ordersDTO, USER_ID);
        assertThat(resultOrdersDTO.timestamp().toLocalDate()).isEqualTo(LocalDateTime.now().toLocalDate());
        assertThat(resultOrdersDTO.totalPrice()).isEqualTo(1800);
    }

    @Test
    void createOrderThrowException() {
        OrdersDTO ordersDTO = new OrdersDTO(null, null, TICKET_CATEGORY_ID, 3, null);

        assertThatThrownBy(() -> ordersService.createOrder(ordersDTO, USER_ID))
                .isInstanceOf(Exception.class)
                .hasMessage("no event found for eventId=" + ordersDTO.eventId());
    }
}
