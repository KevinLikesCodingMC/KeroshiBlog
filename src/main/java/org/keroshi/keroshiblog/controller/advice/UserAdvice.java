package org.keroshi.keroshiblog.controller.advice;

import jakarta.servlet.http.HttpSession;
import org.keroshi.keroshiblog.dto.CurrentUser;
import org.keroshi.keroshiblog.service.UserService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserAdvice {
	private final UserService userService;

	public UserAdvice(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("currentUser")
	public CurrentUser currentUser(HttpSession session) {
		return userService.getCurrentUser(session);
	}
}
