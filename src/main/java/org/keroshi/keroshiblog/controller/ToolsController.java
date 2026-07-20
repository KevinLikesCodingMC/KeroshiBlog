package org.keroshi.keroshiblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToolsController {
	@RequestMapping("tools/codemirror")
	public String toolCodemirror() {
		return "tools/codemirror";
	}
}
