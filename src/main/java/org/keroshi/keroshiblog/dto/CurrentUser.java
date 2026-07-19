package org.keroshi.keroshiblog.dto;

import org.keroshi.keroshiblog.domain.User;

import java.time.Instant;

public record CurrentUser (
		Long id,
		String username,
		boolean admin,
		Instant registerTime,
		Instant lastLoginTime
) {
	public static CurrentUser from(User user) {
		return new CurrentUser(
				user.getId(),
				user.getUsername(),
				user.isAdmin(),
				user.getRegisterTime(),
				user.getLastLoginTime()
		);
	}
}
