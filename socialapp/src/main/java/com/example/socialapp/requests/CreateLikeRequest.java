package com.example.socialapp.requests;

import lombok.Data;

@Data
public class CreateLikeRequest {

	Long id;
	Long postId;
	Long userId;
}
