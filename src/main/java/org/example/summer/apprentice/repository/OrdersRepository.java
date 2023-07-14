package org.example.summer.apprentice.repository;

import org.example.summer.apprentice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o  where o.user.userId=?1")
    Optional<List<Orders>> findOrdersByUserId(long customerId);
}
