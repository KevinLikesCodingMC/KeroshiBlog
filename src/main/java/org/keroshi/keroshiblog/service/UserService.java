package org.keroshi.keroshiblog.service;

import org.keroshi.keroshiblog.domain.User;
import org.keroshi.keroshiblog.repository.UserRepository;
import org.keroshi.keroshiblog.service.result.RegisterResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	public UserService(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder
	) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	private static final Pattern USERNAME_PATTERN
			= Pattern.compile("^[a-zA-Z0-9_.-]+$");
	private static final Pattern PASSWORD_PATTERN =
			Pattern.compile("^[ -~]+$");

	public RegisterResult register(
			String username,
			String password
	) {
		if (username == null || username.isBlank()) {
			return new RegisterResult(
					false,
					"USERNAME_EMPTY",
					null
			);
		}

		if (username.length() < 3) {
			return new RegisterResult(
					false,
					"USERNAME_TOO_SHORT",
					null
			);
		}

		if (username.length() > 64) {
			return new RegisterResult(
					false,
					"USERNAME_TOO_LONG",
					null
			);
		}

		if (! USERNAME_PATTERN.matcher(username).matches()) {
			return new RegisterResult(
					false,
					"USERNAME_INVALID",
					null
			);
		}

		String usernameNormalized =
				username.toLowerCase(Locale.ROOT);

		if (userRepository.existsByUsernameNormalized(usernameNormalized)) {
			return new RegisterResult(
					false,
					"USERNAME_EXISTS",
					null
			);
		}

		if (password == null || password.isBlank()) {
			return new RegisterResult(
					false,
					"PASSWORD_EMPTY",
					null
			);
		}

		if (password.length() < 8) {
			return new RegisterResult(
					false,
					"PASSWORD_TOO_SHORT",
					null
			);
		}

		if (password.length() > 64) {
			return new RegisterResult(
					false,
					"PASSWORD_TOO_LONG",
					null
			);
		}

		if (! PASSWORD_PATTERN.matcher(password).matches()) {
			return new RegisterResult(
					false,
					"PASSWORD_INVALID",
					null
			);
		}

		String passwordHash = passwordEncoder.encode(password);

		var user = new User();
		user.setUsername(username);
		user.setUsernameNormalized(usernameNormalized);
		user.setPasswordHash(passwordHash);
		user.setAdmin(userRepository.count() == 0);
		user.setRegisterTime(Instant.now());
		userRepository.save(user);

		return new RegisterResult(
				true,
				"SUCCESS",
				user
		);
	}
}
