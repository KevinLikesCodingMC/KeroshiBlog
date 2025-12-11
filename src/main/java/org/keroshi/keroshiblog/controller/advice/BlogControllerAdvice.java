package org.keroshi.keroshiblog.controller.advice;

import org.keroshi.keroshiblog.config.BlogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class BlogControllerAdvice {
	private final BlogProperties blogProperties;
	public BlogControllerAdvice(BlogProperties blogProperties) {
		this.blogProperties = blogProperties;
	}

	@ModelAttribute
	public void addBlogAttributes(Model model) {
		model.addAttribute("blog", blogProperties);
	}
}
