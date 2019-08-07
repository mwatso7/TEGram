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
	public boolean savePost(Post newPost, int user_id) {
		String tagStr = newPost.getTags()[0];
		
		for (int i = 1; i < newPost.getTags().length; i++) {
			tagStr += "," + newPost.getTags()[i];
		}
		
		try{
			int id = getNextId();
			String sqlInsertPost = "INSERT INTO post(id, title, img_url, username, tags) VALUES (?,?,?,?,?)";
			jdbcTemplate.update(sqlInsertPost, id, newPost.getTitle(), newPost.getImg_url(), newPost.getUsername(), tagStr);
		} catch (Exception e){
			return false;
		}
		return true;
		
	}
	
	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('post_post_id_seq')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		int id;
		if (results.next()) {
			id = results.getInt(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}
	
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

}
