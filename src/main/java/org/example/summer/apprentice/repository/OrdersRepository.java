package org.example.summer.apprentice.repository;

import org.example.summer.apprentice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o inner join Event e on o.event.eventId = e.eventId and e.venue.venueId=?1 where e.eventType.eventTypeName=?2")
    Optional<List<Orders>> findOrdersByVenueIdAndEventType(long venueId,  String eventTypeName);
}
