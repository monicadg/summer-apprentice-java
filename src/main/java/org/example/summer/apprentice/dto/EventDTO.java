package org.example.summer.apprentice.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.List;


@JsonSerialize
public record EventDTO(Long id,
                       VenueDTO venue,
                       String type,
                       String description,
                       String name,
                       LocalDate startDate,
                       LocalDate endDate,
                       List<TicketCategoryDTO> ticketCategories) {
}
