package org.keroshi.keroshiblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogIndexController {
	@RequestMapping("")
	String index() {
		return "index";
	}
}
