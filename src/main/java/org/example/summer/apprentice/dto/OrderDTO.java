package org.example.summer.apprentice.dto;

import java.time.LocalDateTime;

public record OrderDTO(Long eventId,
                       LocalDateTime timestamp,
                       Long ticketCategoryId,
                       Integer numberOfTickets,
                       Float totalPrice) {
}
