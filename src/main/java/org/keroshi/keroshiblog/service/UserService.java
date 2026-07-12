package org.keroshi.keroshiblog.service;

import jakarta.transaction.Transactional;
import org.keroshi.keroshiblog.domain.InviteCode;
import org.keroshi.keroshiblog.domain.User;
import org.keroshi.keroshiblog.repository.UserRepository;
import org.keroshi.keroshiblog.result.Result;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final InviteCodeService inviteCodeService;
	private final PasswordEncoder passwordEncoder;
	public UserService(
			UserRepository userRepository,
			InviteCodeService inviteCodeService,
			PasswordEncoder passwordEncoder
	) {
		this.userRepository = userRepository;
		this.inviteCodeService = inviteCodeService;
		this.passwordEncoder = passwordEncoder;
	}

	private static final Pattern USERNAME_PATTERN
			= Pattern.compile("^[a-zA-Z0-9_.-]+$");
	private static final Pattern PASSWORD_PATTERN =
			Pattern.compile("^[ -~]+$");

	@Transactional
	public Result<User> register(
			String username,
			String password,
			String inviteCode
	) {
		if (username == null || username.isBlank()) {
			return Result.fail("USERNAME_EMPTY");
		}

		if (username.length() < 3) {
			return Result.fail("USERNAME_TOO_SHORT");
		}

		if (username.length() > 64) {
			return Result.fail("USERNAME_TOO_LONG");
		}

		if (! USERNAME_PATTERN.matcher(username).matches()) {
			return Result.fail("USERNAME_INVALID");
		}

		String usernameNormalized =
				username.toLowerCase(Locale.ROOT);

		if (userRepository.existsByUsernameNormalized(usernameNormalized)) {
			return Result.fail("USERNAME_EXISTS");
		}

		if (password == null || password.isBlank()) {
			return Result.fail("PASSWORD_EMPTY");
		}

		if (password.length() < 8) {
			return Result.fail("PASSWORD_TOO_SHORT");
		}

		if (password.length() > 64) {
			return Result.fail("PASSWORD_TOO_LONG");
		}

		if (! PASSWORD_PATTERN.matcher(password).matches()) {
			return Result.fail("PASSWORD_INVALID");
		}

		boolean firstUser = userRepository.count() == 0;
		InviteCode invite = null;

		if (! firstUser) {
			var inviteResult = inviteCodeService.use(inviteCode);

			if (! inviteResult.success()) {
				return Result.fail(inviteResult.code());
			}

			invite = inviteResult.data();
		}

		String passwordHash = passwordEncoder.encode(password);

		var user = new User();
		user.setUsername(username);
		user.setUsernameNormalized(usernameNormalized);
		user.setPasswordHash(passwordHash);
		user.setAdmin(firstUser);
		user.setRegisterTime(Instant.now());
		userRepository.save(user);

		if (invite != null) {
			invite.setUsedBy(user);
		}

		return Result.ok(user);
	}
}
