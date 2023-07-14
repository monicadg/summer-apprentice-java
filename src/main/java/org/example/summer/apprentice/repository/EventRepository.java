package org.example.summer.apprentice.repository;

import org.example.summer.apprentice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Events
 */
public interface EventRepository  extends JpaRepository<Event, Long> {

    /**
     * Finds Events based on venue and eventTypeName
     * @param venueId
     * @param eventTypeName
     * @return Optional<List<Event>>
     */
    @Query("select e from Event e where  e.venue.venueId=?1 and e.eventType.eventTypeName=?2")
    Optional<List<Event>> findByVenueIdAndEventType(long venueId, String eventTypeName);
}
