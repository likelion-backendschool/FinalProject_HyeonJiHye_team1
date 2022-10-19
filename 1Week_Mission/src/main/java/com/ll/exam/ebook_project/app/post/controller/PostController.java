package com.ll.exam.ebook_project.app.post.controller;

import com.ll.exam.ebook_project.app.member.entity.Member;
import com.ll.exam.ebook_project.app.post.entity.Post;
import com.ll.exam.ebook_project.app.post.form.PostForm;
import com.ll.exam.ebook_project.app.post.service.PostService;
import com.ll.exam.ebook_project.app.security.dto.MemberContext;
import com.ll.exam.ebook_project.util.Ut;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/post")
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    public String showWrite() {
        return "post/write";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write")
    public String doWrite(@AuthenticationPrincipal MemberContext memberContext, @Valid PostForm postForm) {
        Member author = memberContext.getMember();

        Post post = postService.doWrite(author, postForm.getSubject(), postForm.getContent());

        return "redirect:/post/list" + post.getId() + "?msg=" + Ut.url.encode("%d번 글이 생성되었습니다.".formatted(post.getId()));

    }

}
