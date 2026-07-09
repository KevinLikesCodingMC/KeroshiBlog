package org.keroshi.keroshiblog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keroshi.keroshiblog.domain.InviteCode;
import org.keroshi.keroshiblog.repository.InviteCodeRepository;

import static org.mockito.Mockito.mock;

public class InviteCodeServiceTest {
	private InviteCodeRepository inviteCodeRepository;
	private InviteCodeService inviteCodeService;

	@BeforeEach
	void setUp() {
		inviteCodeRepository = mock(InviteCodeRepository.class);
		inviteCodeService = new InviteCodeService(inviteCodeRepository);
	}

	@Test
	void generateCode() {
		for (int i = 0; i < 5; i ++) {
			InviteCode invite = inviteCodeService.generate();
			System.out.println(invite.getCode());
		}
	}
}
