package org.keroshi.keroshiblog.controller.request;

public record LoginRequest (
	String username,
	String password
) { }
