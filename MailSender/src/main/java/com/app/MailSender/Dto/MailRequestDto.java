package com.app.MailSender.Dto;

public class MailRequestDto {
	
	private String subject;
	private String message;
	private String cc;
	private String bcc;
	private Long senderId;
	private Long receiverId;
	
	
	public MailRequestDto(String subject, String message, String cc, String bcc, Long senderId, Long receiverId) {
		super();
		this.subject = subject;
		this.message = message;
		this.cc = cc;
		this.bcc = bcc;
		this.senderId = senderId;
		this.receiverId = receiverId;
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


	public Long getSenderId() {
		return senderId;
	}


	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}


	public Long getReceiverId() {
		return receiverId;
	}


	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}


	@Override
	public String toString() {
		return "MailRequestDto [subject=" + subject + ", message=" + message + ", cc=" + cc + ", bcc=" + bcc
				+ ", senderId=" + senderId + ", receiverId=" + receiverId + "]";
	}
	
	
	
	
	
}
