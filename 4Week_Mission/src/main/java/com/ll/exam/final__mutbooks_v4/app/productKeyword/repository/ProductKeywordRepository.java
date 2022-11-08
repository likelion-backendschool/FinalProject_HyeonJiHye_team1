package com.ll.exam.final__mutbooks_v4.app.productKeyword.repository;


import com.ll.exam.final__mutbooks_v4.app.productKeyword.entity.ProductKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductKeywordRepository extends JpaRepository<ProductKeyword, Long> {
    Optional<ProductKeyword> findByContent(String keywordContent);
}
