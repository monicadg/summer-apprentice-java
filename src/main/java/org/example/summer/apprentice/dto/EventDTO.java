package org.example.summer.apprentice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public record EventDTO(Long id, VenueDTO venue, String type, String description, String name, String startDate,
                       String endDate,  List<TicketCategoryDTO> ticketCategories) {


}
