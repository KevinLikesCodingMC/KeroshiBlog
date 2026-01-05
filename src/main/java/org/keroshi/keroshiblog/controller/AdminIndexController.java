package org.keroshi.keroshiblog.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.keroshi.keroshiblog.domain.Content;
import org.keroshi.keroshiblog.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AdminIndexController {
	private final ContentService contentService;
	public AdminIndexController(ContentService contentService) {
		this.contentService = contentService;
	}

	@RequestMapping("/admin/index_content")
	public String adminIndexContent(HttpServletRequest request, Model model) {
		contentService.checkContentByName("index");
		Content content = contentService.getContentByName("index").get();
		model.addAttribute("content", content.getContent());
		return "admin/index_content";
	}

	@RequestMapping("/admin/index_content/check")
	public String adminIndexContentCheck(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		contentService.checkContentByName("index");
		Content content = contentService.getContentByName("index").get();
		content.setContent((String) map.get("content"));
		contentService.saveContent(content);
		return "redirect:/admin/index_content";
	}

}