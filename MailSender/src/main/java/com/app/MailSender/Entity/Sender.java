package com.app.MailSender.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sender")
public class Sender {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long senderId;
	private String name;
	private String email;
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "sender")
	@JsonIgnore
	private List<Mail>mails;

	public Sender(Long senderId, String name, String email, LocalDateTime createdAt, List<Mail> mails) {
		super();
		this.senderId = senderId;
		this.name = name;
		this.email = email;
		this.createdAt = createdAt;
		this.mails = mails;
	}
	
	public Sender() {
		
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Mail> getMails() {
		return mails;
	}

	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}

	@Override
	public String toString() {
		return "Sender [senderId=" + senderId + ", name=" + name + ", email=" + email + ", createdAt=" + createdAt
				+ ", mails=" + mails + "]";
	}
	
	

}
