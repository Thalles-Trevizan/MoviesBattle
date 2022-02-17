package com.letscode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

}