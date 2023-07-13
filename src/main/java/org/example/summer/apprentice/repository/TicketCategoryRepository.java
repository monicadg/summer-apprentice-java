package org.example.summer.apprentice.repository;

import org.example.summer.apprentice.model.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory,Long> {


    @Query("Select tk from TicketCategory tk where tk.event.eventId=?1")
    TicketCategory findTicketCategoryByEventId(Long id);
}
