package org.keroshi.keroshiblog.controller;

import jakarta.servlet.http.HttpSession;
import org.keroshi.keroshiblog.domain.Article;
import org.keroshi.keroshiblog.domain.User;
import org.keroshi.keroshiblog.dto.ArticleResponse;
import org.keroshi.keroshiblog.service.ArticleService;
import org.keroshi.keroshiblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;
import java.util.Optional;


@Controller
public class ArticleController {
	private final ArticleService articleService;
	private final UserService userService;
	public ArticleController(
			ArticleService articleService,
			UserService userService
	) {
		this.articleService = articleService;
		this.userService = userService;
	}

	@RequestMapping("/article/manage")
	public String articleManage(
			HttpSession session
	) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/login";
		}

		return "article/manage";
	}

	@RequestMapping("/article/edit/{id}")
	public String articleEdit(
			@PathVariable(value = "id") Long id,
			HttpSession session,
			Model model
	) {
		Optional<User> userOptional = userService.getUser(session);
		if (userOptional.isEmpty()) {
			return "redirect:/login";
		}

		Optional<Article> articleOptional = articleService.getArticleById(id);
		if (articleOptional.isEmpty()) {
			return "redirect:/";
		}

		User user = userOptional.get();
		Article article = articleOptional.get();

		if (! user.isAdmin() &&
				! Objects.equals(article.getAuthor().getId(), user.getId())) {
			return "redirect:/";
		}

		var response = ArticleResponse.from(article);

		model.addAttribute("article", response);
		model.addAttribute("admin", user.isAdmin());

		return "article/edit";
	}

	@RequestMapping("/article/view/{id}")
	public String articleView(
			@PathVariable(value = "id") Long id,
			HttpSession session,
			Model model
	) {
		Optional<Article> articleOptional = articleService.getArticleById(id);
		if (articleOptional.isEmpty()) {
			return "redirect:/";
		}

		Article article = articleOptional.get();

		if (article.isHidden()) {
			Optional<User> userOptional = userService.getUser(session);
			if (userOptional.isEmpty()) {
				return "redirect:/";
			}

			User user = userOptional.get();
			if (! user.isAdmin() &&
					! Objects.equals(article.getAuthor().getId(), user.getId())) {
				return "redirect:/";
			}
		}

		var response = ArticleResponse.from(article);
		model.addAttribute("article", response);

		return "article/view";
	}

}
