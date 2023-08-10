package com.example.socialapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.entities.Like;
import com.example.socialapp.requests.CreateLikeRequest;
import com.example.socialapp.responses.LikeResponse;
import com.example.socialapp.services.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {
	private LikeService likeService;

	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}

	@GetMapping
	public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
		return likeService.getAllLikes(userId, postId);
	}

	@GetMapping("/{likeId}")
	public Like getLikeById(@PathVariable Long likeId) {
		return likeService.getLikeById(likeId);
	}

	@DeleteMapping("/{likeId}")
	public void deleteLikeById(@PathVariable Long likeId) {
		likeService.deleteLikeById(likeId);
	}

	@PostMapping
	public Like createOneLike(@RequestBody CreateLikeRequest request) {
		return likeService.createOneLike(request);
	}
}
