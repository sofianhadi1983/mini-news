package com.alterra.miniproject.mininews.repository;

import com.alterra.miniproject.mininews.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findAllByTitle(String title);
}
