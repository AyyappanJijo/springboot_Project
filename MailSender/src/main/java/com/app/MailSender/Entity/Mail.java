package com.app.MailSender.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mail")
public class Mail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mailId;
	private String subject;
	private String message;
	private String cc;
	private String bcc;
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private Sender sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private Receiver receiver;

	public Mail(Long mailId, String subject, String message, String cc, String bcc, LocalDateTime createdAt,
			Sender sender, Receiver receiver) {
		super();
		this.mailId = mailId;
		this.subject = subject;
		this.message = message;
		this.cc = cc;
		this.bcc = bcc;
		this.createdAt = createdAt;
		this.sender = sender;
		this.receiver = receiver;
	}

	public Mail() {
	}

	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "Mail [mailId=" + mailId + ", subject=" + subject + ", message=" + message + ", cc=" + cc + ", bcc="
				+ bcc + ", createdAt=" + createdAt + ", sender=" + sender + ", receiver=" + receiver + "]";
	}
	
	

}
