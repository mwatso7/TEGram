package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
	private int post_id;
	private String title;
	private LocalDateTime date_time;
	private String img_url;
	private String username;
	private String[] tags;
	private List<Comment> comments;
	
	public int getPost_id() {
		return post_id;
	}
	
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LocalDateTime getDate_time() {
		return date_time;
	}
	
	public void setDate_time(LocalDateTime date_time) {
		this.date_time = date_time;
	}
	
	public String getImg_url() {
		return img_url;
	}
	
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String[] getTags() {
		return tags;
	}
	
	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment>  comments) {
		this.comments = comments;
	}
	
	
	
}


