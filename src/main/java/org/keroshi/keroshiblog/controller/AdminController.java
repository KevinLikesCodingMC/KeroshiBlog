package org.keroshi.keroshiblog.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.keroshi.keroshiblog.config.BlogProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Objects;

@Controller
public class AdminController {
	private final BlogProperties blogProperties;
	public AdminController(BlogProperties blogProperties) {
		this.blogProperties = blogProperties;
	}

	@RequestMapping("/admin")
	public String adminIndex() {
		return "admin/index";
	}

	@RequestMapping("/admin/login")
	public String adminLogin() {
		return "admin/login";
	}

	@RequestMapping("/admin/login/check")
	public String adminLoginCheck(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		if (! Objects.equals(map.get("username"), blogProperties.getSuUsername())
		|| ! Objects.equals(map.get("password"), blogProperties.getSuPassword())) {
			return "redirect:/admin/login";
		}
		request.getSession().setAttribute("admin", "ok");
		return "redirect:/admin";
	}

	@RequestMapping("/admin/logout")
	public String adminLogout(HttpServletRequest request) {
		request.getSession().removeAttribute("admin");
		return "redirect:/admin/login";
	}
}
