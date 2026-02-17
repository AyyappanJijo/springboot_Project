package com.app.MailSender.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Receiver")
public class Receiver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long receiverId;
	private String name;
	private String email;
	
	@OneToMany(mappedBy = "receiver")
	@JsonIgnore
	private List<Mail>mails;

	public Receiver(Long receiverId, String name, String email, List<Mail> mails) {
		super();
		this.receiverId = receiverId;
		this.name = name;
		this.email = email;
		this.mails = mails;
	}
	
	public Receiver() {
		
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
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

	public List<Mail> getMails() {
		return mails;
	}

	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}

	@Override
	public String toString() {
		return "Receiver [receiverId=" + receiverId + ", name=" + name + ", email=" + email + ", mails=" + mails + "]";
	}
	
	

}
