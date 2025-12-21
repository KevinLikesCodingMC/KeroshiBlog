package org.keroshi.keroshiblog.mapper;

import org.keroshi.keroshiblog.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	List<Article> findByHiddenFalse();

	Page<Article> findByHiddenFalse(Pageable pageable);
}
