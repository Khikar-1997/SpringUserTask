package com.example.demo.persistance.repository;

import com.example.demo.persistance.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
