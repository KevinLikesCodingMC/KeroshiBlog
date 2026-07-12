package org.keroshi.keroshiblog.controller.request;

public record RegisterRequest (
	String username,
	String password,
	String inviteCode
) { }
