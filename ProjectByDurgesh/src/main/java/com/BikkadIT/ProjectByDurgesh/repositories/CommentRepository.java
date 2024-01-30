package com.BikkadIT.ProjectByDurgesh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BikkadIT.ProjectByDurgesh.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
