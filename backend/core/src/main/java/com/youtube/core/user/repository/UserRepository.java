package com.youtube.core.user.repository;

import com.youtube.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(final String email);
}
