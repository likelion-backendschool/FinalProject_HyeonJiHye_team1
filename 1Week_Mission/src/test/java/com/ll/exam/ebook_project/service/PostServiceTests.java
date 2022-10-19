package com.ll.exam.ebook_project.service;

import com.ll.exam.ebook_project.app.member.entity.Member;
import com.ll.exam.ebook_project.app.member.repository.MemberRepository;
import com.ll.exam.ebook_project.app.post.entity.Post;
import com.ll.exam.ebook_project.app.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class PostServiceTests {
    @Autowired
    private PostService postService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("글 작성 후 업로드")
    void t1() {
        Member author = memberRepository.findByUsername("user1").get();

        Post post = postService.doWrite(author, "제목", "내용");

        assertThat(post).isNotNull();
        assertThat(post.getSubject()).isEqualTo("제목");
        assertThat(post.getContent()).isEqualTo("내용");
    }
}
