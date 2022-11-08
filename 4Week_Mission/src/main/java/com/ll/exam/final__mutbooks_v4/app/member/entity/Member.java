package com.ll.exam.final__mutbooks_v4.app.member.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.final__mutbooks_v4.app.base.entity.BaseEntity;
import com.ll.exam.final__mutbooks_v4.app.member.entity.emum.AuthLevel;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private boolean emailVerified;
    private long restCash;
    private String nickname;

    @Convert(converter = AuthLevel.Converter.class)
    private AuthLevel authLevel;

    public String getName() {
        if (nickname != null) {
            return nickname;
        }

        return username;
    }

    public Member(long id) {
        super(id);
    }

    public String getJdenticon() {
        return "member__" + getId();
    }

    public List<GrantedAuthority> genAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        // nickname을 가지면 -> 작가 권한 부여
        if (StringUtils.hasText(nickname)) {
            authorities.add(new SimpleGrantedAuthority("AUTHOR"));
        }

        // username이 admin1이라면 -> 관리자 권한 부여
        if (getUsername().equals("admin1")) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return authorities;
    }
}
