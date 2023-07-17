package org.example.summer.apprentice.repository;

import org.example.summer.apprentice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Order
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds Orders for specified user
     *
     * @param userId
     * @return Optional<List < Orders>>
     */
    @Query("select o from Order o where o.user.userId=?1")
    Optional<List<Order>> findOrdersByUserId(long userId);
}
