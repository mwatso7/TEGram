package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Post;
import com.techelevator.model.PostDao;


@RestController
@CrossOrigin
public class PostController {

	private PostDao postDao;
	
	public PostController(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@GetMapping
	public List<Post> getFeed() {
		return postDao.getAllPosts();
	}
}
