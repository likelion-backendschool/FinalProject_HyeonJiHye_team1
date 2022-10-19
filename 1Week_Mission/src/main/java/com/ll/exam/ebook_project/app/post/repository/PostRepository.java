package com.ll.exam.ebook_project.app.post.repository;

import com.ll.exam.ebook_project.app.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
