package org.example.summer.apprentice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

@JsonSerialize
public record OrdersDTO(Long id, VenueDTO venueDTO, String type, String description, String name, Date startDate,
                        Date endDate, List<TicketCategoryDTO> ticketCategoryDTO) {


}
