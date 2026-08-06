package org.keroshi.keroshiblog.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.keroshi.keroshiblog.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class AdminInterceptor implements HandlerInterceptor {
	private final UserService userService;
	public AdminInterceptor(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler
	) throws Exception {

		String redirect = URLEncoder.encode(
				request.getRequestURI(),
				StandardCharsets.UTF_8
		);

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(
					request.getContextPath()
					+ "/login?redirect=" + redirect
			);
			return false;
		}

		var user = userService.getCurrentUser(session);

		if (user == null) {
			response.sendRedirect(
					request.getContextPath()
					+ "/login?redirect=" + redirect
			);
			return false;
		}

		if (! user.admin()) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}

		return true;
	}
}
