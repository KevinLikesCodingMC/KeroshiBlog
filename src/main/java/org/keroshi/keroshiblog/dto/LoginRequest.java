package org.keroshi.keroshiblog.dto;

public record LoginRequest (
	String username,
	String password
) { }
