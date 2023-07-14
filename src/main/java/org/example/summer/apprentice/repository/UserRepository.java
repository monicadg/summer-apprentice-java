package org.example.summer.apprentice.repository;

import org.example.summer.apprentice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Users
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
