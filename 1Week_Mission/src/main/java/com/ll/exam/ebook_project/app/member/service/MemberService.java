package com.ll.exam.ebook_project.app.member.service;

import com.ll.exam.ebook_project.app.member.entity.Member;
import com.ll.exam.ebook_project.app.member.exception.AlreadyJoinException;
import com.ll.exam.ebook_project.app.member.form.ModifyForm;
import com.ll.exam.ebook_project.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    public Member join(String username, String password, String email, String nickname) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new AlreadyJoinException();
        }

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .build();
        memberRepository.save(member);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("[멋북스] - 회원 가입 축하 메일입니다.");
            mimeMessageHelper.setText("멋북스의 회원이 되신 것을 환영합니다.", false);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }

        return member;
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findByEmail(String email) {

        return memberRepository.findByEmail(email);
    }

    public void modify(Member member, String nickname) {
        member.setNickname(nickname);

        memberRepository.save(member);

    }

}