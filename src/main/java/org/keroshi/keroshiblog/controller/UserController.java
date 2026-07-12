package org.keroshi.keroshiblog.controller;

import org.keroshi.keroshiblog.controller.request.RegisterRequest;
import org.keroshi.keroshiblog.domain.User;
import org.keroshi.keroshiblog.result.Result;
import org.keroshi.keroshiblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/register")
	public String register() {
		return "user/register";
	}

	@ResponseBody
	@RequestMapping("/api/register")
	public Result<User> registerApi(
			@RequestBody RegisterRequest request
			) {
		return userService.register(
				request.username(),
				request.password(),
				request.inviteCode()
		);
	}
}
