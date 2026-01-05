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

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class AdminArticleController {
	private final ArticleService articleService;
	public AdminArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/admin/article/new")
	String adminArticleNew() {
		return "admin/article/new";
	}

	@RequestMapping("/admin/article/new/check")
	String adminArticleNewCheck(@RequestParam Map<String,Object> map) {
		Article article = new Article();
		article.setName((String) map.get("name"));
		article.setTitle((String) map.get("title"));
		article.setContent((String) map.get("content"));
		article.setCreateTime(new Date());
		article.setUpdateTime(new Date());
		article.setHidden(map.containsKey("hidden"));
		articleService.saveArticle(article);
		return "redirect:/admin/article/edit/" + article.getId();
	}

	@RequestMapping("/admin/article_list")
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
		return "admin/article/list";
	}

	@RequestMapping("/admin/article/edit/{id}")
	String adminArticleEdit(@PathVariable(value = "id") long id, Model model) {
		Optional<Article> article = articleService.getArticleById(id);
		if (article.isEmpty()) return "redirect:/admin";
		model.addAttribute("article", article.get());
		return "admin/article/edit";
	}

	@RequestMapping("/admin/article/edit/check/{id}")
	String adminArticleEditCheck(@PathVariable(value = "id") long id, @RequestParam Map<String,Object> map) {
		Optional<Article> article = articleService.getArticleById(id);
		if (article.isEmpty()) return "redirect:/admin";
		article.get().setName((String) map.get("name"));
		article.get().setTitle((String) map.get("title"));
		article.get().setContent((String) map.get("content"));
		article.get().setUpdateTime(new Date());
		article.get().setHidden(map.containsKey("hidden"));
		articleService.saveArticle(article.get());
		return "redirect:/admin/article/edit/" + id;
	}
}
