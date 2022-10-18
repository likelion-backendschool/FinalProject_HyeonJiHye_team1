package com.ll.exam.ebook_project.app.base.initData;

import com.ll.exam.ebook_project.app.member.entity.Member;
import com.ll.exam.ebook_project.app.member.service.MemberService;

public interface InitDataBefore {
    default void before(MemberService memberService) {
        Member member1 = memberService.join("user1", "1234", "user1@test.com", "author");
        Member member2 = memberService.join("user2", "1234", "user2@test.com", "author2");
    }
}
