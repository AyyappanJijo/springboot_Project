package com.Confess_Your_Feelings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ConfessionDTO {

    private Long id;

    @NotBlank(message = "Sender name is required")
    private String senderName;

    @NotBlank(message = "Receiver name is required")
    private String receiverName;

    @NotBlank(message = "Message cannot be empty")
    @Size(max = 500, message = "Message must not exceed 500 characters")
    private String message;

    private String mood;

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

  
}