package org.keroshi.keroshiblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping("/admin")
	public String adminIndex() {
		return "redirect:/admin/dashboard";
	}

	@RequestMapping("/admin/dashboard")
	public String adminDashboard(Model model) {

		Runtime runtime = Runtime.getRuntime();
		long maxMemory = runtime.maxMemory();
		long totalMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		long usedMemory = totalMemory - freeMemory;
		long memoryPercent = usedMemory * 100 / maxMemory;
		long memoryMB = usedMemory / 1024 / 1024;

		model.addAttribute("memoryPercent", memoryPercent);
		model.addAttribute("memoryMB", memoryMB);

		return "admin/dashboard";
	}
}
