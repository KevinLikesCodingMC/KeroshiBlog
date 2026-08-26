package org.keroshi.keroshiblog.dto;

public record RegisterRequest (
	String username,
	String password,
	String inviteCode
) { }
