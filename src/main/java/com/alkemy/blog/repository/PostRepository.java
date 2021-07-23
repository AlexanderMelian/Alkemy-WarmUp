package com.alkemy.blog.repository;

import java.util.List;

import com.alkemy.blog.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByTitle(String title);

    public List<Post> findByCategory(String category);

}