package org.keroshi.keroshiblog.controller;

import jakarta.servlet.http.HttpSession;
import org.keroshi.keroshiblog.domain.Article;
import org.keroshi.keroshiblog.domain.User;
import org.keroshi.keroshiblog.dto.ArticleResponse;
import org.keroshi.keroshiblog.dto.EditArticleRequest;
import org.keroshi.keroshiblog.result.Result;
import org.keroshi.keroshiblog.service.ArticleService;
import org.keroshi.keroshiblog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ArticleApiController {
	private final ArticleService articleService;
	private final UserService userService;
	public ArticleApiController(
			ArticleService articleService,
			UserService userService
	) {
		this.articleService = articleService;
		this.userService = userService;
	}

	@PostMapping("/api/article/new")
	public Result<?> apiArticleNew(
			HttpSession session
	) {
		Optional<User> userOptional = userService.getUser(session);

		if (userOptional.isEmpty()) {
			return Result.fail("401 UNAUTHORIZED");
		}

		User user = userOptional.get();
		articleService.createArticle(user);

		return Result.ok();
	}

	@GetMapping("/api/article/list/me")
	public Result<List<ArticleResponse>> apiArticleListMe(
			HttpSession session
	) {
		Optional<User> userOptional = userService.getUser(session);

		if (userOptional.isEmpty()) {
			return Result.fail("401 UNAUTHORIZED");
		}

		User user = userOptional.get();
		List<Article> articles = articleService.getArticlesByUser(user);

		List<ArticleResponse> articleResponses = articles
				.stream()
				.map(ArticleResponse :: from)
				.collect(Collectors.toList());

		return Result.ok(articleResponses);
	}

	@PostMapping("/api/article/edit")
	public Result<?> apiArticleEdit(
			HttpSession session,
			@RequestBody EditArticleRequest request
	) {
		Optional<User> userOptional = userService.getUser(session);
		if (userOptional.isEmpty()) {
			return Result.fail("401 UNAUTHORIZED");
		}

		Optional<Article> articleOptional = articleService.getArticleById(request.id());
		if (articleOptional.isEmpty()) {
			return Result.fail("ARTICLE_NOT_EXIST");
		}

		User user = userOptional.get();
		Article article = articleOptional.get();

		if (! user.isAdmin() &&
				! Objects.equals(article.getAuthor().getId(), user.getId())) {
			return Result.fail("403 FORBIDDEN");
		}

		article.setName(request.name());
		article.setTitle(request.title());
		article.setContent(request.content());
		article.setHidden(request.hidden());
		article.setUpdateTime(Instant.now());

		if (! user.isAdmin()) {
			article.setHidden(true);
		}

		articleService.saveArticle(article);

		return Result.ok();
	}

}
