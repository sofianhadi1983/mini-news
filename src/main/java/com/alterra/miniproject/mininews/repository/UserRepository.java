package com.alterra.miniproject.mininews.repository;

import com.alterra.miniproject.mininews.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
