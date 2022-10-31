package com.ll.exam.final__mutbooks_v2.app.postkeyword.repository;

import com.ll.exam.final__mutbooks_v2.app.postkeyword.entity.PostKeyword;

import java.util.List;

public interface PostKeywordRepositoryCustom {
    List<PostKeyword> getQslAllByAuthorId(Long authorId);
}
