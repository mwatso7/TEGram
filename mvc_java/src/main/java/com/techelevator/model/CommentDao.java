package com.techelevator.model;

import java.util.List;

public interface CommentDao {
	
	public List<Comment> getCommentsByPostId(int post_id);
	
	public Comment getFirstCommentByPostId(int post_id);

}
