package com.alterra.miniproject.mininews.repository;

import com.alterra.miniproject.mininews.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
