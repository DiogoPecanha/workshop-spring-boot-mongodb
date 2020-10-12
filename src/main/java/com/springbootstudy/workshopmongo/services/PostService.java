package com.springbootstudy.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootstudy.workshopmongo.domain.Post;
import com.springbootstudy.workshopmongo.repositories.PostRepository;
import com.springbootstudy.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Post findById(String id) {
		
		Optional<Post> post = postRepository.findById(id);		
		return post.orElseThrow(() -> new ObjectNotFoundException("User not found"));		
	}
	
	public List<Post> findByTitle(String text){
		return postRepository.findByTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + (24 * 60 * 1000));
		return postRepository.fullSearch(text, minDate, maxDate);
	}
}
