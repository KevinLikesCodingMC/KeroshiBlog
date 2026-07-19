package org.keroshi.keroshiblog.controller.advice;

import jakarta.servlet.http.HttpSession;
import org.keroshi.keroshiblog.dto.CurrentUser;
import org.keroshi.keroshiblog.repository.UserRepository;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserAdvice {
	private final UserRepository userRepository;

	public UserAdvice(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@ModelAttribute("currentUser")
	public CurrentUser currentUser(HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return null;
		}

		var user = userRepository.findById(userId);
		return user.map(CurrentUser :: from).orElse(null);
	}
}
