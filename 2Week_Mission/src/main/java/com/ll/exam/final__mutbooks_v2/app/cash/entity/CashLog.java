package com.ll.exam.final__mutbooks_v2.app.cash.entity;

import com.ll.exam.final__mutbooks_v2.app.base.entity.BaseEntity;
import com.ll.exam.final__mutbooks_v2.app.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class CashLog extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member member;

    private long price; // 변동가격

    private String eventType; //변동종류

    public CashLog(long id) {
        super(id);
    }
}
