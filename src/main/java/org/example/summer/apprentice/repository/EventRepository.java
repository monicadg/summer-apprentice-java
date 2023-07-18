package org.example.summer.apprentice.repository;

import org.example.summer.apprentice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Events
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Finds Events based on venue and eventTypeName
     *
     * @param venueId
     * @param eventTypeName
     * @return the events
     */
    @Query("SELECT e FROM Event e WHERE e.venue.venueId=?1 AND e.eventType.eventTypeName=?2")
    Optional<List<Event>> findByVenueIdAndEventType(long venueId, String eventTypeName);

    /**
     * Finds Events based on venueId
     *
     * @param venueId
     * @return the events
     */
    @Query("SELECT e FROM Event e WHERE e.venue.venueId=?1")
    Optional<List<Event>> findByVenueId(long venueId);

    /**
     * Finds Events based on event type
     *
     * @param eventType
     * @return the events
     */
    @Query("SELECT e FROM Event e WHERE e.eventType.eventTypeName=?1")
    Optional<List<Event>> findByEventType(String eventType);
}
