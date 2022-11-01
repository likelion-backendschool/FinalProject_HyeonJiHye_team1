package com.ll.exam.final__mutbooks_v3.app.rebate.repository;

import com.ll.exam.final__mutbooks_v3.app.rebate.entity.RebateOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RebateOrderItemRepository extends JpaRepository<RebateOrderItem, Long> {
    Optional<RebateOrderItem> findByOrderItemId(long orderItemId);
}
