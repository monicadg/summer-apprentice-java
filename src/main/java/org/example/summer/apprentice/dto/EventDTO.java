package org.example.summer.apprentice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public record EventDTO(Long id, VenueDTO venueDTO, String type, String description, String name, String startDate,
                       String endDate, List<TicketCategoryDTO> ticketCategoryDTO) {


}
