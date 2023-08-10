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

import com.example.socialapp.entities.Comment;
import com.example.socialapp.requests.CommentCreateRequest;
import com.example.socialapp.requests.CommentUpdateRequest;
import com.example.socialapp.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping
	public List<Comment> getAllComment(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
		return commentService.getAllCommentWithParam(userId, postId);
	}

	@GetMapping("/{commentId}")
	public Comment getCommentById(@PathVariable Long commentId) {
		return commentService.getCommentById(commentId);
	}

	@PostMapping
	public Comment createOneComment(@RequestBody CommentCreateRequest request) {
		return commentService.createOneComment(request);
	}

	@PutMapping("/{commentId}")
	public Comment updateCommentById(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request) {
		return commentService.updateCommentById(commentId, request);
	}

	@DeleteMapping("/{commentId}")
	public void deleteCommentById(@PathVariable Long commentId) {
		commentService.deleteCommentById(commentId);
	}

}
