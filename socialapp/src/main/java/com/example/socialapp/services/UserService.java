package com.example.socialapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.socialapp.entities.Comment;
import com.example.socialapp.entities.Like;
import com.example.socialapp.entities.User;
import com.example.socialapp.repos.CommentRepository;
import com.example.socialapp.repos.LikeRepository;
import com.example.socialapp.repos.PostRepository;
import com.example.socialapp.repos.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private LikeRepository likeRepository;
	private CommentRepository commentRepository;
	private PostRepository postRepository;

	public UserService(UserRepository userRepository, LikeRepository likeRepository,
			CommentRepository commentRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.likeRepository = likeRepository;
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}

	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User updateUserById(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			foundUser.setAvatar(newUser.getAvatar());
			userRepository.save(foundUser);
			return foundUser;
		} else {
			return null;
		}
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public List<Object> getUserActivity(Long userId) {
		List<Long> postIds = postRepository.findTopUserId(userId);
		if (postIds.isEmpty())
			return null;

		List<Object> c = commentRepository.findUserCommentsByPostId(postIds);
		List<Object> l = likeRepository.findUserCommentsByPostId(postIds);
		List<Object> res = new ArrayList<>();
		res.addAll(c);
		res.addAll(l);
		return res;
	}

}
