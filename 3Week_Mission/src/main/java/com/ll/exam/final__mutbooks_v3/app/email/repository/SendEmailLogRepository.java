package com.ll.exam.final__mutbooks_v3.app.email.repository;

import com.ll.exam.final__mutbooks_v3.app.email.entity.SendEmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendEmailLogRepository extends JpaRepository<SendEmailLog, Long> {
}
