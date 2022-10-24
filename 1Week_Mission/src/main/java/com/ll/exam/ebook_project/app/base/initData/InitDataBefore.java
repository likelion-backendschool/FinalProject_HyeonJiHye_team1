package com.ll.exam.ebook_project.app.base.initData;

import com.ll.exam.ebook_project.app.member.entity.Member;
import com.ll.exam.ebook_project.app.member.service.MemberService;
import com.ll.exam.ebook_project.app.post.entity.Post;
import com.ll.exam.ebook_project.app.post.service.PostService;

public interface InitDataBefore {
    default void before(MemberService memberService, PostService postService) {
        Member member1 = memberService.join("user1", "1234", "user1@test.com", "author");
        Member member2 = memberService.join("user2", "1234", "user2@test.com", "author2");

        postService.doWrite(member1, "글 1", "내용 1");
        postService.doWrite(member1, "글 2", "내용 2");
        postService.doWrite(member2, "글 3", "내용 3");
        postService.doWrite(member2, "글 4", "내용 4");
    }
}
