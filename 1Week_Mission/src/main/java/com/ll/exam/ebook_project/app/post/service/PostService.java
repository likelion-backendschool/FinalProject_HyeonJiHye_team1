package com.ll.exam.ebook_project.app.post.service;

import com.ll.exam.ebook_project.app.member.entity.Member;
import com.ll.exam.ebook_project.app.post.entity.Post;
import com.ll.exam.ebook_project.app.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public Post doWrite(Member author, String subject, String content) {
        Post post = Post.builder()
                .subject(subject)
                .content(content)
                .author(author)
                .build();

        postRepository.save(post);

        return post;

    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        Post post = postRepository.findById(id).orElse(null);

        return post;
    }

}
