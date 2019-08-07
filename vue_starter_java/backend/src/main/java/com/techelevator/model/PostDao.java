package com.techelevator.model;

import java.util.List;

public interface PostDao {
	
	public List<Post> getAllPosts();
	
	public Post getPostByPostId(int id);
	
	public List<Post> getPostsByUsername(String username);
	
	public boolean savePost(Post newPost, int user_id);
}
