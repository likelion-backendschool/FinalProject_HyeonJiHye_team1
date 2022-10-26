package com.ll.exam.final__mutbooks_v2.app.cash.repository;

import com.ll.exam.final__mutbooks_v2.app.cash.entity.CashLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashLogRepository extends JpaRepository<CashLog, Long> {
}
