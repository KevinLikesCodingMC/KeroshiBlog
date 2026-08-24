package org.keroshi.keroshiblog.controller;

import jakarta.servlet.http.HttpSession;
import org.keroshi.keroshiblog.domain.Article;
import org.keroshi.keroshiblog.domain.User;
import org.keroshi.keroshiblog.dto.ArticleResponse;
import org.keroshi.keroshiblog.result.Result;
import org.keroshi.keroshiblog.service.ArticleService;
import org.keroshi.keroshiblog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}
