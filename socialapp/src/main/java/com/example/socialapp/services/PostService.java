package com.example.socialapp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialapp.entities.Like;
import com.example.socialapp.entities.Post;
import com.example.socialapp.entities.User;
import com.example.socialapp.repos.PostRepository;
import com.example.socialapp.requests.PostCreateRequest;
import com.example.socialapp.requests.PostUpdateRequest;
import com.example.socialapp.responses.LikeResponse;
import com.example.socialapp.responses.PostResponse;

@Service
public class PostService {
	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;

	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;

	}

	@Autowired
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list;

		if (userId.isPresent())
			list = postRepository.findByUserId(userId.get());
		list = postRepository.findAll();
		return list.stream().map(p -> {
			List<LikeResponse> likes = likeService.getAllLikes(Optional.ofNullable(null), Optional.of(p.getId()));
			return new PostResponse(p, likes);
		}).collect(Collectors.toList());

	}

	public Post getPostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}
	
	public PostResponse getPostByIdWithLikes(Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		List<LikeResponse> likes = likeService.getAllLikes(Optional.ofNullable(null), Optional.of(postId));
		return new PostResponse(post, likes);
	}
	

	public Post createOnePost(PostCreateRequest newPostRequest) {
		User user = userService.getUserById(newPostRequest.getUserId());
		if (user == null)
			return null;

		Post toSave = new Post();
		toSave.setId(newPostRequest.getId());
		toSave.setText(newPostRequest.getText());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setUser(user);
		toSave.setCreateDate(new Date());
		return postRepository.save(toSave);
	}

	public Post updatePostById(Long postId, PostUpdateRequest postUpdateRequest) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(postUpdateRequest.getText());
			toUpdate.setTitle(postUpdateRequest.getTitle());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deletePostById(Long postId) {
		postRepository.deleteById(postId);
	}

}
