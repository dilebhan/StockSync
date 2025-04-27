package com.stocksync.repository;

import com.stocksync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmailId(String email);
    boolean existsByUserEmailId(String email);
}
