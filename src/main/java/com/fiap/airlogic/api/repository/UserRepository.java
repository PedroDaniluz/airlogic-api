package com.fiap.airlogic.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fiap.airlogic.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
