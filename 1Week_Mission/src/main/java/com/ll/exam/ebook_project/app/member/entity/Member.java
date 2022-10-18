package com.ll.exam.ebook_project.app.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.ebook_project.app.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    @Column(unique = true)
    private String email;
    private String nickname;
    @Column
    private Integer authLevel;

    public String getName() {
        return username;
    }
    public Member(long id) {
        super(id);
    }
}