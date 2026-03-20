package com.Confess_Your_Feelings;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Confession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderName;
    private String receiverName;
    private String message;
    private String mood;

    public Confession() {}

    public Confession(String senderName, String receiverName, String message, String mood) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.mood = mood;
    }
    
 // Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	
	@Override
	public String toString() {
		return "Confession [id=" + id + ", senderName=" + senderName + ", receiverName=" + receiverName + ", message="
				+ message + ", mood=" + mood + "]";
	}

    
 
}
