package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.exception.PostNotFoundException;
import com.techelevator.model.Comment;
import com.techelevator.model.CommentDao;

@RestController
@CrossOrigin
@RequestMapping("/api/comment")
public class CommentController {
	
	private CommentDao commentDao;
	
	public CommentController(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
//	@GetMapping("/first/{post_id}")
//	public Comment getFirstComment(@PathVariable int post_id) throws PostNotFoundException {
//		Comment comment = commentDao.getFirstCommentByPostId(post_id);
//		if (comment != null) {
//			return comment;
//		} else {
//			throw new PostNotFoundException(post_id, "Post Not Found!");
//		}
//	}
	
	@GetMapping("/all/{post_id}")
	public List<Comment> getAllComments(@PathVariable int post_id) throws PostNotFoundException {
		List<Comment> comments = commentDao.getCommentsByPostId(post_id);
		if (comments != null) {
			return comments;
		} else {
			throw new PostNotFoundException(post_id, "Post Not Found!");
		}
	}
}
