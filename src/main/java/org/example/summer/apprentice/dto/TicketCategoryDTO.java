package org.example.summer.apprentice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.summer.apprentice.model.enums.TicketCategoryType;

@JsonSerialize
public record TicketCategoryDTO(Long id, TicketCategoryType ticketCategoryType, Float price) {
}
