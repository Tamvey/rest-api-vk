package com.test.web.controller;

import com.test.web.dto.post.Post;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class PostController {
    @Value("${authority}")
    private String authority;


    @GetMapping("/api/posts")
    public ResponseEntity<List> getAll(@RequestBody(required = false) Post body,
                                       HttpMethod method,
                                       HttpServletRequest request)
            throws URISyntaxException {
        URI uri = new URI("https", authority, "/posts", request.getQueryString(), null);
        return new RestTemplate().exchange(uri, method, new HttpEntity<>(body), List.class);
    }
    @GetMapping("/api/posts/{id}")
    public ResponseEntity<Post> getOne(@PathVariable("id") int id,
                                       HttpServletRequest request)
            throws URISyntaxException {
        URI uri = new URI("https", authority, "/posts/" + id, request.getQueryString(), null);
        return new ResponseEntity<>(new RestTemplate().getForObject(uri, Post.class), HttpStatus.OK);
    }

    @PostMapping("/api/posts")
    public ResponseEntity<Post> newPost(@RequestBody Post body,
                                     HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI("https", authority, "/posts", request.getQueryString(), null);
        return new ResponseEntity<>(new RestTemplate().postForObject(uri, body, Post.class), HttpStatus.CREATED);
    }
    @PutMapping("/api/posts/{id}")
        public ResponseEntity<Post> updatePost(@RequestBody Post body,
                                            @PathVariable("id") int id,
                                            HttpMethod method,
                                            HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI("https", authority, "/posts/" + id, request.getQueryString(), null);
        return new RestTemplate().exchange(uri, method, new HttpEntity<>(body), Post.class);
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Post> deletePost(@RequestBody(required = false) Post body,
                                        @PathVariable("id") int id,
                                        HttpMethod method,
                                        HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI("https", authority, "/posts/" + id, request.getQueryString(), null);
        return new RestTemplate().exchange(uri, method, new HttpEntity<>(body), Post.class);
    }

}
