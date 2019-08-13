package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcPostDao implements PostDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcPostDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Post> getAllPosts() {
		List<Post> thePosts = new ArrayList<Post>();

		String sqlGetAllPosts = "SELECT * FROM post ORDER BY post_id DESC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllPosts);

		while (results.next()) {
			Post thePost = mapRowToPost(results);
			thePosts.add(thePost);
		}
		return thePosts;
	}

	@Override
	public Post getPostByPostId(int id) {
		Post thePost = new Post();
		String sqlSelectAllPosts = "SELECT * FROM post WHERE post_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllPosts, id);
		if (results.next()) {
		thePost = mapRowToPost(results);
		}
		return thePost;
	}

	@Override
	public List<Post> getPostsByUsername(String username) {
		List<Post> thePosts = new ArrayList<Post>();
		String sqlSelectAllPosts = "SELECT * FROM post WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllPosts, username);
		
		while (results.next()) {
			Post thePost = mapRowToPost(results);
			thePosts.add(thePost);
		}
		return thePosts;
	}

	@Override
	public boolean savePost(Post newPost) {
		String tagStr = "";
		if(newPost.getTags().length > 0) {
			tagStr = newPost.getTags()[0];
			
			for (int i = 1; i < newPost.getTags().length; i++) {
				tagStr += "," + newPost.getTags()[i];
			}
		}
		
		try{
			
			String sqlInsertPost = "INSERT INTO post(title, img_url, username, tags) VALUES (?,?,?,?)";
			jdbcTemplate.update(sqlInsertPost, newPost.getTitle(), newPost.getImg_url(), newPost.getUsername(), tagStr);
		} catch (Exception e){
			return false;
		}
		return true;
		
	}
	
//	private int getNextId() {
//		String sqlSelectNextId = "SELECT NEXTVAL('post_post_id_seq')";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
//		int id;
//		if (results.next()) {
//			id = results.getInt(1);
//		} else {
//			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
//		}
//		return id;
//	}
	
	private Post mapRowToPost(SqlRowSet row) {
		Post thePost = new Post();
		thePost.setPost_id(row.getInt("post_id"));
		thePost.setTitle(row.getString("title"));
		thePost.setDate_time(row.getTimestamp("date_time").toLocalDateTime());
		thePost.setImg_url(row.getString("img_url"));
		thePost.setUsername(row.getString("username"));
		thePost.setTags(row.getString("tags").split(","));
		return thePost;
	}

	@Override
	public boolean saveLike(int id, String username) {
		try {
		String saveToLikesTable = "INSERT INTO likes(post_id, username) VALUES(?, ?)";
		jdbcTemplate.update(saveToLikesTable, id, username);
		
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteLike(int id, String username) {
		try {
			String deleteFromLikesTable = "DELETE FROM likes WHERE post_id = ? AND username = ?";
			jdbcTemplate.update(deleteFromLikesTable, id, username);
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public int numberOfLikesOnPost(int id) {
		
		String pullLikesFromTable = "SELECT post_id, COUNT(post_id) as count FROM likes WHERE post_id = ? GROUP BY post_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(pullLikesFromTable, id);
		if(results.next()) {
			return results.getInt("count");
		}
		return 0;
		
		
	}

	@Override
	public boolean saveFavorite(int id, String username) {
		try {
			String saveToFavoritesTable = "INSERT INTO favorites(post_id, username) VALUES(?, ?)";
			jdbcTemplate.update(saveToFavoritesTable, id, username);
			
			}catch (Exception e) {
				return false;
			}
			return true;
		
		
	}

	@Override
	public boolean deleteFavorite(int id, String username) {
		try {
			String deleteFromLikesTable = "DELETE FROM favorites WHERE post_id = ? AND username = ?";
			jdbcTemplate.update(deleteFromLikesTable, id, username);
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List<Post> favoritesFromUser(String username) {
		List<Post> listOfFavorites = new ArrayList<Post>();
		try {
			String favoritesByUser = "SELECT * FROM post WHERE post_id IN (Select post_id FROM favorites WHERE username = ?)";
			SqlRowSet results = jdbcTemplate.queryForRowSet(favoritesByUser, username);
			while(results.next()) {
				Post thePost = mapRowToPost(results);
				listOfFavorites.add(thePost);
			}
		}catch (Exception e) {
			return null;
		}
		
		return listOfFavorites;
	}

	@Override
	public boolean isLikedByUser(int id, String username) {
		try {
			String doesLikeExist = "SELECT * FROM likes Where post_id = ? AND username = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(doesLikeExist, id, username);
			if(results.next()) {
				return true;
			} 
			else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean isFavoritedFromUser(int id, String username) {
		try {
			String doesFavoriteExist = "SELECT * FROM favorites Where post_id = ? AND username = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(doesFavoriteExist, id, username);
			if(results.next()) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		
	}
}
