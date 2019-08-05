package com.techelevator.model;

import java.util.List;

public interface PostDao {
	
	public List<Post> getAllPosts();
	
	public Post getPostByPostId(int id);
	
	public List<Post> getPostsByUserId(int id);
	
	public boolean savePost(Post newPost, int user_id);
}
