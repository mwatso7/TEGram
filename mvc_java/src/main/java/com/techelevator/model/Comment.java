package com.techelevator.model;

import java.time.LocalDateTime;

public class Comment {
	private int comment_id;
	private String user_name;
	private LocalDateTime date_time;
	private String comment;
	
	public int getComment_id() {
		return comment_id;
	}
	
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUsername(String user_name) {
		this.user_name = user_name;
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
