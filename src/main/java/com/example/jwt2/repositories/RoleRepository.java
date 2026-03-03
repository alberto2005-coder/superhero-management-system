package com.example.jwt2.repositories;

import com.example.jwt2.entities.Role;
import com.example.jwt2.entities.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}