package com.alterra.miniproject.mininews.repository;

import com.alterra.miniproject.mininews.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
