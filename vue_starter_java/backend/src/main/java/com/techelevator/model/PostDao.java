package com.techelevator.model;

import java.util.List;

public interface PostDao {
	
	public List<Post> getAllPosts();
	
	public Post getPostByPostId(int id);
	
	public List<Post> getPostsByUsername(String username);
	
	public boolean savePost(Post newPost);
	
	public boolean saveLike(int id, String username);
	
	public boolean deleteLike(int id, String username);

	public int numberOfLikesOnPost(int id);
	
	public boolean isLikedByUser(int id, String username);
	
	public boolean saveFavorite(int id, String username);
	
	public boolean deleteFavorite(int id, String username);
	
	public List<Post> favoritesFromUser(String username);
	
	public boolean isFavoritedFromUser(int id, String username);
}
