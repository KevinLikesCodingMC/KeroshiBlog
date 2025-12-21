package org.keroshi.keroshiblog.service;

import org.keroshi.keroshiblog.domain.Article;
import org.keroshi.keroshiblog.mapper.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;
	ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Override
	public void saveArticle(Article article) {
		this.articleRepository.save(article);
	}

	@Override
	public Optional<Article> getArticleById(long id) {
		return this.articleRepository.findById(id);
	}

	@Override
	public void deleteArticleById(long id) {
		this.articleRepository.deleteById(id);
	}

	@Override
	public List<Article> getAllArticles() {
		return this.articleRepository.findAll();
	}

	@Override
	public Page<Article> getArticleByPage(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}

	@Override
	public List<Article> getAllVisibleArticles() {
		return this.articleRepository.findByHiddenFalse();
	}

	@Override
	public Page<Article> getVisibleArticleByPage(Pageable pageable) {
		return articleRepository.findByHiddenFalse(pageable);
	}
}
