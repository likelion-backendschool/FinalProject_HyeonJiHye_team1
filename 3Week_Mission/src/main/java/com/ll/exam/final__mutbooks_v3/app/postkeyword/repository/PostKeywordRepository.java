package com.ll.exam.final__mutbooks_v3.app.postkeyword.repository;


import com.ll.exam.final__mutbooks_v3.app.postkeyword.entity.PostKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostKeywordRepository extends JpaRepository<PostKeyword, Long>, PostKeywordRepositoryCustom {
    Optional<PostKeyword> findByContent(String keywordContent);
}
