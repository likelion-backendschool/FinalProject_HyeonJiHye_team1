package com.ll.exam.final__mutbooks_v2.app.order.repository;

import com.ll.exam.final__mutbooks_v2.app.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByBuyerId(Long id);
}
