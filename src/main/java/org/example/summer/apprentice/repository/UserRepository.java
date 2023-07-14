package org.example.summer.apprentice.repository;

import org.example.summer.apprentice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
