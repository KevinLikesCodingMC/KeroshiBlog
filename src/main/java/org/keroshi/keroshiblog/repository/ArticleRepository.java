package org.keroshi.keroshiblog.repository;

import org.keroshi.keroshiblog.domain.Article;
import org.keroshi.keroshiblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByAuthor(User author);

	List<Article> findByAuthorId(Long authorId);
}
