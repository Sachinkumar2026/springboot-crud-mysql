package com.example.CRUD.repository;

import com.example.CRUD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByNameContainingIgnoreCase(String name);
    Optional<User> findByEmail(String email);
}
