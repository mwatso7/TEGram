package com.techelevator.model;

import java.util.List;

public interface CommentDao {
	
	public List<Comment> getCommentsByPostId(int post_id);
	
	public boolean saveComment(Comment comment);
	
	public boolean deleteComments(int post_id);

}
