package com.evervc.dev.inventorymanagement.repository;

import com.evervc.dev.inventorymanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
