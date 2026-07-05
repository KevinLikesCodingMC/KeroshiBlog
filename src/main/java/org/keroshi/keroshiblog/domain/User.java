package org.keroshi.keroshiblog.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String usernameNormalized;

	@Column(nullable = false)
	private String passwordHash;

	@Column(nullable = false)
	private boolean admin = false;

	@Column(nullable = false, updatable = false)
	private Instant registerTime;

	@Column
	private Instant lastLoginTime;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Instant getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Instant registerTime) {
		this.registerTime = registerTime;
	}

	public Instant getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Instant lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getUsernameNormalized() {
		return usernameNormalized;
	}

	public void setUsernameNormalized(String usernameNormalized) {
		this.usernameNormalized = usernameNormalized;
	}
}
