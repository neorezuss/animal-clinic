package com.example.animal.clinic.back.repository;

import com.example.animal.clinic.back.entity.Role;
import com.example.animal.clinic.back.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(RoleEnum name);
}
