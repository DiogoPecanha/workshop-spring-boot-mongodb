package com.springbootstudy.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootstudy.workshopmongo.domain.Post;
import com.springbootstudy.workshopmongo.resources.util.URL;
import com.springbootstudy.workshopmongo.services.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = postService.findById(id);

		return ResponseEntity.ok(post);
	}
	
	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);

		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		
		text = URL.decodeParam(text);
		Date dateIni = URL.convertDate(minDate, new Date(0L));
		Date dateMax = URL.convertDate(maxDate, new Date());

		List<Post> list = postService.fullSearch(text, dateIni, dateMax)				;
		return ResponseEntity.ok(list);
	}
}
