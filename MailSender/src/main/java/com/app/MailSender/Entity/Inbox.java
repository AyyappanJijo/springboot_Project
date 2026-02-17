package com.app.MailSender.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inbox")
public class Inbox {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inboxId;
	private String email;
	private Integer unreadCount;
	private LocalDateTime lastUpdated;
	
	public Inbox(Long inboxId, String email, Integer unreadCount, LocalDateTime lastUpdated) {
		super();
		this.inboxId = inboxId;
		this.email = email;
		this.unreadCount = unreadCount;
		this.lastUpdated = lastUpdated;
	}
	
	public Inbox() {
		
	}

	public Long getInboxId() {
		return inboxId;
	}

	public void setInboxId(Long inboxId) {
		this.inboxId = inboxId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(Integer unreadCount) {
		this.unreadCount = unreadCount;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "Inbox [inboxId=" + inboxId + ", email=" + email + ", unreadCount=" + unreadCount + ", lastUpdated="
				+ lastUpdated + "]";
	}
	

}
