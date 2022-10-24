package com.ll.exam.ebook_project.controller;

import com.ll.exam.ebook_project.app.member.controller.MemberController;
import com.ll.exam.ebook_project.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class MemberControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 폼")
    void t1() throws Exception {
        ResultActions resultActions = mvc
                .perform(get("/member/join"))
                .andDo(print());

        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("showJoin"))
                .andExpect(content().string(containsString("회원가입")));
    }

    @Test
    @DisplayName("회원가입")
    void t2() throws Exception {
        ResultActions resultActions = mvc
                .perform(post("/member/join")
                        .with(csrf())
                        .param("username", "user999")
                        .param("password", "1234")
                        .param("passwordCheck", "1234")
                        .param("email", "user999@test.com")
                        .param("nickname", "author999")
                )
                .andDo(print());

        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(redirectedUrlPattern("/member/login?msg=**"));

        assertThat(memberService.findByUsername("user999").isPresent()).isTrue();
    }

    @Test
    @DisplayName("회원 프로필 - 작가명 변경 폼")
    @WithUserDetails("user1")
    void t3() throws Exception {
        ResultActions resultActions = mvc
                .perform(get("/member/modify"))
                .andDo(print());

        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("showModify"))
                .andExpect(content().string(containsString("작가명 변경")));
    }

    @Test
    @DisplayName("회원 프로필 - 작가명 변경")
    @WithUserDetails("user1")
    void t4() throws Exception {
        ResultActions resultActions = mvc
                .perform(post("/member/modify")
                        .with(csrf())
                        .param("username", "user1")
                        .param("email", "user1@test.com")
                        .param("nickname", "authOne")
                )
                .andDo(print());

        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("modify"))
                .andExpect(redirectedUrlPattern("/member/profile"))
                .andExpect(content().string(containsString("authOne")));


        assertThat(memberService.findByUsername("user1").isPresent()).isTrue();
    }
}
