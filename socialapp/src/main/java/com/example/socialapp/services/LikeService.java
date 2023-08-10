package com.example.socialapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.socialapp.entities.Like;
import com.example.socialapp.entities.Post;
import com.example.socialapp.entities.User;
import com.example.socialapp.repos.LikeRepository;

import com.example.socialapp.requests.CreateLikeRequest;
import com.example.socialapp.responses.LikeResponse;

@Service
public class LikeService {
	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;

	public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<LikeResponse> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
		List<Like> list;

		if (userId.isPresent() && postId.isPresent()) {
			list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
		} else if (userId.isPresent()) {
			list = likeRepository.findByUserId(userId.get());
		} else if (postId.isPresent()) {
			list = likeRepository.findByPostId(postId.get());
		} else {
			list = likeRepository.findAll();
		}

		return list.stream().map(l -> new LikeResponse(l)).collect(Collectors.toList());

	}

	public Like getLikeById(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}

	public void deleteLikeById(Long likeId) {
		likeRepository.deleteById(likeId);
	}

	public Like createOneLike(CreateLikeRequest request) {
		User user = userService.getUserById(request.getUserId());
		Post post = postService.getPostById(request.getPostId());
		if (user != null && post != null) {
			Like likeToSave = new Like();
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			likeToSave.setId(request.getId());
			return likeRepository.save(likeToSave);
		} else
			return null;
	}

}
