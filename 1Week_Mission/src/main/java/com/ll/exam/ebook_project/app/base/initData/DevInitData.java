package com.ll.exam.ebook_project.app.base.initData;

import com.ll.exam.ebook_project.app.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevInitData implements InitDataBefore {

    @Bean
    CommandLineRunner initData(MemberService memberService) {

        return args -> {
            before(memberService);
        };
    }
}
