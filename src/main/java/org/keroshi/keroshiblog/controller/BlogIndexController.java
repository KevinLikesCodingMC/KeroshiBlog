package org.keroshi.keroshiblog.controller;

import org.keroshi.keroshiblog.domain.Content;
import org.keroshi.keroshiblog.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogIndexController {
	private final ContentService contentService;
	public BlogIndexController(ContentService contentService) {
		this.contentService = contentService;
	}

	@RequestMapping("/")
	String index(Model model) {
		contentService.checkContentByName("index");
		Content content = contentService.getContentByName("index").get();
		model.addAttribute("content", content.getContent());
		return "blog/index";
	}
}
