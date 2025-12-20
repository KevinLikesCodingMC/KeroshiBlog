package org.keroshi.keroshiblog.controller;

import org.keroshi.keroshiblog.domain.Article;
import org.keroshi.keroshiblog.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BlogArticleController {
	private final ArticleService articleService;
	public BlogArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/blog_list")
	public String blogList(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Article> articlePage = articleService.getArticleByPage(pageable);
		model.addAttribute("articles", articlePage);
		model.addAttribute("currentPage", pageable.getPageNumber());
		model.addAttribute("totalPages", articlePage.getTotalPages());
		model.addAttribute("totalItems", articlePage.getTotalElements());
		model.addAttribute("hasNext", articlePage.hasNext());
		model.addAttribute("hasPrevious", articlePage.hasPrevious());
		model.addAttribute("pageSize", pageable.getPageSize());
		return "blog/list";
	}

	@RequestMapping("/article/{id}")
	public String articleView(@PathVariable(value = "id") long id, Model model) {
		Optional<Article> article = articleService.getArticleById(id);
		if(article.isEmpty()) {
			return "redirect:/";
		}
		model.addAttribute("article", article.get());
		return "blog/article";
	}
}
