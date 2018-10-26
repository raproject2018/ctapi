package com.cta.contact.contactapi.DTO;

import java.io.Serializable;

public class MessageDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private boolean isSuccessful;

	public MessageDTO(String message,boolean isSuccessful) {
			this.message = message;
			this.isSuccessful = isSuccessful;
	}
	
	public MessageDTO() {
		}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
  
}
