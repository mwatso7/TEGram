package com.techelevator.model;

import java.time.LocalDateTime;

public class Comment {
	private int comment_id;
	private String username;
	private LocalDateTime date_time;
	private String comment;
	
	public int getComment_id() {
		return comment_id;
	}
	
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public LocalDateTime getDate_time() {
		return date_time;
	}
	
	public void setDate_time(LocalDateTime date_time) {
		this.date_time = date_time;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
