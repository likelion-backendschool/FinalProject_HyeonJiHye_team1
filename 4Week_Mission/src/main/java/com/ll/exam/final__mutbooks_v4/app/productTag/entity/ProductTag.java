package com.ll.exam.final__mutbooks_v4.app.productTag.entity;

import com.ll.exam.final__mutbooks_v4.app.base.entity.BaseEntity;
import com.ll.exam.final__mutbooks_v4.app.member.entity.Member;
import com.ll.exam.final__mutbooks_v4.app.product.entity.Product;
import com.ll.exam.final__mutbooks_v4.app.productKeyword.entity.ProductKeyword;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class ProductTag extends BaseEntity {
    @ManyToOne
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne
    @ToString.Exclude
    private Member member;

    @ManyToOne
    @ToString.Exclude
    private ProductKeyword productKeyword;
}
