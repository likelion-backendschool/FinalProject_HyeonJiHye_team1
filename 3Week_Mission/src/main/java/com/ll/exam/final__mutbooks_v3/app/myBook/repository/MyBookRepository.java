package com.ll.exam.final__mutbooks_v3.app.myBook.repository;

import com.ll.exam.final__mutbooks_v3.app.myBook.entity.MyBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {
    void deleteByProductIdAndOwnerId(long productId, long ownerId);
}
