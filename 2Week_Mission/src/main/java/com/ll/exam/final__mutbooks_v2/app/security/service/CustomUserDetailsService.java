package com.ll.exam.final__mutbooks_v2.app.security.service;

import com.ll.exam.final__mutbooks_v2.app.member.entity.Member;
import com.ll.exam.final__mutbooks_v2.app.member.repository.MemberRepository;
import com.ll.exam.final__mutbooks_v2.app.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).get();

        return new MemberContext(member, member.genAuthorities());
    }
}
