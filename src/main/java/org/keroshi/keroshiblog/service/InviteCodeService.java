package org.keroshi.keroshiblog.service;

import org.keroshi.keroshiblog.domain.InviteCode;
import org.keroshi.keroshiblog.domain.User;
import org.keroshi.keroshiblog.repository.InviteCodeRepository;
import org.keroshi.keroshiblog.result.Result;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;

@Service
public class InviteCodeService {
	private final InviteCodeRepository inviteCodeRepository;
	public InviteCodeService(
			InviteCodeRepository inviteCodeRepository
	) {
		this.inviteCodeRepository = inviteCodeRepository;
	}

	private static final String CHARS =
			"ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

	private static final SecureRandom RANDOM
			= new SecureRandom();

	public InviteCode generate() {
		String code;
		do {
			code = generateCode();
		} while (inviteCodeRepository.existsById(code));

		InviteCode invite = new InviteCode();
		invite.setCode(code);
		invite.setCreateTime(Instant.now());
		inviteCodeRepository.save(invite);

		return invite;
	}

	private String generateCode() {
		StringBuilder str = new StringBuilder();

		str.append("KERO-");
		for (int i = 0; i < 4; i ++) {
			str.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
		}
		str.append("-");
		for (int i = 0; i < 4; i ++) {
			str.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
		}

		return str.toString();
	}

	public Result<Void> use(String code, User user) {
		var inviteCode = inviteCodeRepository.findById(code);
		if (inviteCode.isEmpty()) {
			return Result.fail("INVITE_CODE_NOT_EXIST");
		}

		InviteCode invite = inviteCode.get();

		if (invite.isUsed()) {
			return Result.fail("INVITE_CODE_USED");
		}

		invite.setUsed(true);
		invite.setUsedBy(user);
		invite.setUsedTime(Instant.now());
		inviteCodeRepository.save(invite);

		return Result.ok();
	}
}
