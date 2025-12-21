package org.keroshi.keroshiblog.service;

import org.keroshi.keroshiblog.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

	void saveArticle(Article article);

	Optional<Article> getArticleById(long id);

	void deleteArticleById(long id);

	List<Article> getAllArticles();

	Page<Article> getArticleByPage(Pageable pageable);

	List<Article> getAllVisibleArticles();

	Page<Article> getVisibleArticleByPage(Pageable pageable);
}
