package org.keroshi.keroshiblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminArticleController {
	@RequestMapping("/admin/article/new")
	String adminArticleNew() {
		return "admin/article/new";
	}
}
