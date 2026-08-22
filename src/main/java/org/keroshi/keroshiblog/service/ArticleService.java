package org.keroshi.keroshiblog.service;

import org.keroshi.keroshiblog.domain.Article;
import org.keroshi.keroshiblog.domain.User;
import org.keroshi.keroshiblog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ArticleService {
	private final ArticleRepository articleRepository;
	public ArticleService(
			ArticleRepository articleRepository
	) {
		this.articleRepository = articleRepository;
	}

	public void saveArticle(Article article) {
		articleRepository.save(article);
	}

	public Article createArticle(User user) {
		Article article = new Article();

		article.setName("Unnamed");
		article.setAuthor(user);
		article.setHidden(true);
		article.setCreateTime(Instant.now());
		article.setUpdateTime(Instant.now());

		saveArticle(article);
		return article;
	}

	public List<Article> getAllArticles() {
		return this.articleRepository.findAll();
	}
}
