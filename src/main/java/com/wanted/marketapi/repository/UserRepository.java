package com.wanted.marketapi.repository;

import com.wanted.marketapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
