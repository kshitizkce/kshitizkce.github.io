package com.momobowl.projectMomo.users.repositories;

import com.momobowl.projectMomo.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    Users findByEmail(String email);
}