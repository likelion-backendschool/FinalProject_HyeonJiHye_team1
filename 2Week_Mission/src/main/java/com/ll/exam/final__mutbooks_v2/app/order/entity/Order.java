package com.ll.exam.final__mutbooks_v2.app.order.entity;

import com.ll.exam.final__mutbooks_v2.app.base.entity.BaseEntity;
import com.ll.exam.final__mutbooks_v2.app.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "product_order")
public class Order extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member buyer;

    private LocalDateTime payDate; // 결제 날짜

    private boolean readyStatus; // 주문 완료 여부

    private boolean isPaid; // 결제 완료 여부

    private boolean isCanceled; // 취소 여부

    private boolean isRefunded; // 환불 여부

    private String name; // 주문명

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);

        orderItems.add(orderItem);
    }

    public Order(long id) {
        super(id);
    }

    public int calculatePayPrice() {
        int payPrice = 0;

        for (OrderItem orderItem : orderItems) {
            payPrice += orderItem.getSalePrice();
        }

        return payPrice;
    }
}













