package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.exception.PostNotFoundException;
import com.techelevator.exception.UserNotFoundException;
import com.techelevator.model.Post;
import com.techelevator.model.PostDao;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class PostController {

	private PostDao postDao;
	
	public PostController(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@GetMapping("/allposts")
	public List<Post> getFeed() {
		return postDao.getAllPosts();
	}
	
	@GetMapping("/post/{post_id}")
	public Post getPost(@PathVariable int post_id) throws PostNotFoundException {
		Post post = postDao.getPostByPostId(post_id);
		if (post != null) {
			return post;
		} else {
			throw new PostNotFoundException(post_id, "Post Not Found!");
		}
	}
	
	@GetMapping("/userposts/{user_id}")
	public List<Post> getUserPosts(@PathVariable int user_id) throws UserNotFoundException {
		List<Post> posts = postDao.getPostsByUserId(user_id);
		if (posts != null) {
			return posts;
		} else {
			throw new UserNotFoundException(user_id, "User Not Found!");
		}
	}
}
