package com.ll.exam.ebook_project.app.member.form;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class JoinForm {
    @NotEmpty
    private String username;

    @NotEmpty
    private  String password;

    @NotEmpty
    private String email;

    @Nullable
    private String nickname;
}
