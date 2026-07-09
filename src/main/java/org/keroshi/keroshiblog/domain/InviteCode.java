package org.keroshi.keroshiblog.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "invite_code")
public class InviteCode {
	@Id
	@Column(length = 30)
	private String code;

	private boolean used;

	@Column(name = "create_time")
	private Instant createTime;

	@Column(name = "used_time")
	private Instant usedTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "used_by")
	private User usedBy;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Instant getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}

	public Instant getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Instant usedTime) {
		this.usedTime = usedTime;
	}

	public User getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(User usedBy) {
		this.usedBy = usedBy;
	}
}
