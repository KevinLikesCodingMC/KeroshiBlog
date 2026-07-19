package org.keroshi.keroshiblog.controller;

import jakarta.servlet.http.HttpSession;
import org.keroshi.keroshiblog.controller.request.LoginRequest;
import org.keroshi.keroshiblog.controller.request.RegisterRequest;
import org.keroshi.keroshiblog.result.Result;
import org.keroshi.keroshiblog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping("/api/register")
	public Result<Void> registerApi(
			@RequestBody RegisterRequest request
	) {
		var result = userService.register(
				request.username(),
				request.password(),
				request.inviteCode()
		);

		if (result.success()) {
			return Result.ok();
		}
		else {
			return Result.fail(result.code());
		}
	}

	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}

	@ResponseBody
	@PostMapping("/api/login")
	public Result<Void> loginApi(
			@RequestBody LoginRequest request,
			HttpSession session
	) {
		var result = userService.login(
			request.username(),
			request.password()
		);

		if (result.success()) {
			session.setAttribute("userId", result.data().getId());
			return Result.ok();
		}
		else {
			return Result.fail(result.code());
		}
	}

	@ResponseBody
	@PostMapping("/api/logout")
	public Result<Void> logoutApi(
		HttpSession session
	) {
		session.invalidate();
		return Result.ok();
	}
}
