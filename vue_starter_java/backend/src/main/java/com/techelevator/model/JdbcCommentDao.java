package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcCommentDao implements CommentDao{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcCommentDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Comment> getCommentsByPostId(int post_id) {
		List<Comment> comments = new ArrayList<Comment>();
		String sqlSelectAllComments = "SELECT * FROM comments WHERE post_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllComments, post_id);
		while (results.next()) {
			Comment theComment = mapRowToComment(results);
			comments.add(theComment);
		}
		return comments;
	}
	
	@Override
	public boolean saveComment(Comment comment) {
		
		try{
			String sqlInsertComment = "INSERT INTO comments(post_id, username, comment) VALUES (?,?,?)";
			jdbcTemplate.update(sqlInsertComment, comment.getPost_id(), comment.getUsername(), comment.getComment());
		} catch (Exception e){
			return false;
		}
		return true;
		
	}
	
	@Override
	public boolean deleteComments(int post_id) {
		
		try{
			String sqlDeleteComments = "DELETE FROM comments WHERE post_id = ?";
			jdbcTemplate.update(sqlDeleteComments, post_id);
		} catch (Exception e){
			return false;
		}
		return true;
		
	}

//	@Override
//	public Comment getFirstCommentByPostId(int post_id) {
//		Comment theComment = new Comment();
//		String sqlSelectAllComments = "SELECT * FROM comments, post_comments_reference WHERE comments.comment_id = post_comments_reference.comment_id AND post_comments_reference.post_id = ? LIMIT 1";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllComments, post_id);
//		if (results.next()) {
//			theComment = mapRowToComment(results);
//		}
//		return theComment;
//	}
	
	private Comment mapRowToComment(SqlRowSet row) {
		Comment theComment = new Comment();
		theComment.setComment_id(row.getInt("comment_id"));
		theComment.setPost_id(row.getInt("post_id"));
		theComment.setUsername(row.getString("username"));
		theComment.setDate_time(row.getTimestamp("date_time").toLocalDateTime());
		theComment.setComment(row.getString("comment"));
		return theComment;
	}

}
