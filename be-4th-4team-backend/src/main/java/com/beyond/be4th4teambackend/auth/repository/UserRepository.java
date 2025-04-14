package com.beyond.be4th4teambackend.auth.repository;

import com.beyond.be4th4teambackend.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String email);

}
