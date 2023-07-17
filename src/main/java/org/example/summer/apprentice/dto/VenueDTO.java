package org.example.summer.apprentice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record VenueDTO(Long id,
                       String name,
                       String location,
                       Integer capacity) {
}
