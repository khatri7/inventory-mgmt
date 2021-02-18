/**
 * 
 */
package com.practice.inventory.event.domain;

/**
 * @author shanag
 *
 */
public class MessageResponse {
	private long id;
	private String message;
	private String author;
	
	public MessageResponse(long id, String message, String author) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
