package com.bs.msauserservice.repository;

import com.bs.msauserservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findByEmailAndPassword(String email, String password);
    Optional<User>findByEmail(String email);
}
