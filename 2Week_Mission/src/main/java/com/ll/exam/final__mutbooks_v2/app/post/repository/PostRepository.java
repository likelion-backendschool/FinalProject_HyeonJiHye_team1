package com.ll.exam.final__mutbooks_v2.app.post.repository;


import com.ll.exam.final__mutbooks_v2.app.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAuthorIdOrderByIdDesc(long authorId);
}
