package com.youtube.core.user.repository;

import com.youtube.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(final String email);

    Optional<User> findByEmail(final String email);
}
