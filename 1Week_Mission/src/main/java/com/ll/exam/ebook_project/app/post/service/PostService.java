package com.ll.exam.ebook_project.app.post.service;

import com.ll.exam.ebook_project.app.member.entity.Member;
import com.ll.exam.ebook_project.app.post.entity.Post;
import com.ll.exam.ebook_project.app.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//    public Post doWrite(Member member, String subject, String content, String contentHtml) {
//        Post post = Post
//                .builder()
//                .author(member)
//                .subject(subject)
//                .content(content)
//                .contentHtml(contentHtml)
//                .build();
//
//        postRepository.save(post);
//
//        return post;
//    }
}
