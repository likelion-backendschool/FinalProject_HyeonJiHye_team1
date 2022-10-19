package com.ll.exam.ebook_project.app.post.entity;

import com.ll.exam.ebook_project.app.base.entity.BaseEntity;
import com.ll.exam.ebook_project.app.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Post extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member author;

    @Column(unique = true)
    private String subject;

    private String content;

}
