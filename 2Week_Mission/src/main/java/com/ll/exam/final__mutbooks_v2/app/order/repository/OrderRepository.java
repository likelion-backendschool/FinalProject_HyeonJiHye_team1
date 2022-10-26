package com.ll.exam.final__mutbooks_v2.app.order.repository;

import com.ll.exam.final__mutbooks_v2.app.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
