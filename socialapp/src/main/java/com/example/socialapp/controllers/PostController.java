package com.example.socialapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.entities.Post;
import com.example.socialapp.requests.PostCreateRequest;
import com.example.socialapp.requests.PostUpdateRequest;
import com.example.socialapp.responses.PostResponse;
import com.example.socialapp.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping
	public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
		return postService.getAllPosts(userId);
	}

	@GetMapping("/{postId}")
	public PostResponse getPostById(@PathVariable Long postId) {
		return postService.getPostByIdWithLikes(postId);
	}

	@PostMapping
	public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {
		return postService.createOnePost(newPostRequest);
	}

	@PutMapping("/{postId}")
	public Post updatePostById(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
		return postService.updatePostById(postId, postUpdateRequest);
	}

	@DeleteMapping("/{postId}")
	public void deletePostById(@PathVariable Long postId) {
		postService.deletePostById(postId);
	}

}
