package org.keroshi.keroshiblog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.keroshi.keroshiblog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
	private UserRepository userRepository;
	private UserService userService;

	@BeforeEach
	void setUp() {
		PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
		userRepository = mock(UserRepository.class);
		InviteCodeService inviteCodeService = mock(InviteCodeService.class);
		userService = new UserService(
				userRepository,
				inviteCodeService,
				passwordEncoder
		);
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"", " ", "  ", "   ",
	})
	void usernameEmpty(String username) {
		var result = userService.register(username, "keroshi1", "");

		assertFalse(result.success());
		assertEquals("USERNAME_EMPTY", result.code());
	}

	@Test
	void usernameTooShort() {
		var result = userService.register("a", "keroshi1", "");

		assertFalse(result.success());
		assertEquals("USERNAME_TOO_SHORT", result.code());
	}

	@Test
	void usernameTooLong() {
		String username = "a".repeat(65);
		var result = userService.register(username, "keroshi1", "");

		assertFalse(result.success());
		assertEquals("USERNAME_TOO_LONG", result.code());
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"~  ", "!  ",
			"ケロシ",
			"a+b", "a b", "  a", "a  ",
	})
	void usernameInvalid(String username) {
		var result = userService.register(username, "keroshi1", "");

		assertFalse(result.success());
		assertEquals("USERNAME_INVALID", result.code());
	}

	@Test
	void usernameExists() {
		when(userRepository.existsByUsernameNormalized("keroshi"))
				.thenReturn(true);

		var result = userService.register("Keroshi", "keroshi1", "");

		assertFalse(result.success());
		assertEquals("USERNAME_EXISTS", result.code());
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"", " ", "  ", "   ",
	})
	void passwordEmpty(String password) {
		var result = userService.register("Keroshi", password, "");

		assertFalse(result.success());
		assertEquals("PASSWORD_EMPTY", result.code());
	}

	@Test
	void passwordTooShort() {
		var result = userService.register("Keroshi", "keroshi", "");

		assertFalse(result.success());
		assertEquals("PASSWORD_TOO_SHORT", result.code());
	}

	@Test
	void passwordTooLong() {
		String password = "a".repeat(65);
		var result = userService.register("Keroshi", password, "");

		assertFalse(result.success());
		assertEquals("PASSWORD_TOO_LONG", result.code());
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"ケロシケロシケロシ",
	})
	void passwordInvalid(String password) {
		var result = userService.register("Keroshi", password, "");

		assertFalse(result.success());
		assertEquals("PASSWORD_INVALID", result.code());
	}
}
