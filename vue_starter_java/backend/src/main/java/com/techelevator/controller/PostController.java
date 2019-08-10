package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.RequestAuthProvider;
import com.techelevator.exception.PostNotFoundException;
import com.techelevator.exception.UserNotFoundException;
import com.techelevator.model.Comment;
import com.techelevator.model.CommentDao;
import com.techelevator.model.Post;
import com.techelevator.model.PostDao;
import com.techelevator.model.User;


@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostController {

	private PostDao postDao;
	private CommentDao commentDao;
	
	@Autowired
	private AuthProvider auth;
	
	public PostController(PostDao postDao, CommentDao commentDao, AuthProvider auth) {
		this.postDao = postDao;
		this.commentDao = commentDao;
		this.auth = auth;
	}
	
	@GetMapping("/allposts")
	public List<Post> getFeed() {
		List<Post> posts = postDao.getAllPosts();
		for (Post post : posts) {
			post.setComments(commentDao.getCommentsByPostId(post.getPost_id()));
		}
		
		return posts;
	}
	
	@GetMapping("/single_post/{post_id}")
	public Post getPost(@PathVariable int post_id) throws PostNotFoundException {
		Post post = postDao.getPostByPostId(post_id);
		List<Comment> comments = commentDao.getCommentsByPostId(post_id);

		if (post != null && comments != null) {
			post.setComments(comments);
			return post;
		} else {
			throw new PostNotFoundException(post_id, "Post Not Found!");
		}
	}
	
	@GetMapping("/user_posts/{username}")
	public List<Post> getUserPosts(@PathVariable String username) throws UserNotFoundException {
		List<Post> posts = postDao.getPostsByUsername(username);
		if (posts != null) {
			return posts;
		} else {
			throw new UserNotFoundException(username, "User Not Found!");
		}
	}
	
	@RequestMapping(path = "/addpost", method = RequestMethod.POST)
    public void addPost(@RequestBody Post newPost) {
		newPost.setUsername(auth.getCurrentUser().getUsername());
		postDao.savePost(newPost);
	}
}
