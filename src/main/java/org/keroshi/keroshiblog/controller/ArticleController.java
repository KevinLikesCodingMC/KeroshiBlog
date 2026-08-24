package org.keroshi.keroshiblog.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ArticleController {


	@RequestMapping("/article/manage")
	public String articleManage(
			HttpSession session
	) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/login";
		}

		return "article/manage";
	}

}
