package com.test.web.controller;

import com.test.web.dto.post.Post;
import com.test.web.dto.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {
    @Value("${authority}")
    private String authority;


    @GetMapping("/api/users")
    public ResponseEntity<List> getAll(@RequestBody(required = false) User body,
                                       HttpMethod method,
                                       HttpServletRequest request)
            throws URISyntaxException {

        URI uri = new URI("https", authority, "/users", request.getQueryString(), null);
        return new RestTemplate().exchange(uri, method, new HttpEntity<>(body), List.class);
    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") int id,
                                       HttpServletRequest request)
            throws URISyntaxException {
        URI uri = new URI("https", authority, "/users/" + id, request.getQueryString(), null);
        return new ResponseEntity<>(new RestTemplate().getForObject(uri, User.class), HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> newUser(@RequestBody User body,
                                     HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI("https", authority, "/users", request.getQueryString(), null);
        return new ResponseEntity<>(new RestTemplate().postForObject(uri, body, User.class), HttpStatus.CREATED);
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User body,
                                        @PathVariable("id") int id,
                                        HttpMethod method,
                                        HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI("https", authority, "/users/" + id, request.getQueryString(), null);
        return new RestTemplate().exchange(uri, method, new HttpEntity<>(body), User.class);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<User> deleteUser(@RequestBody(required = false) User body,
                                        @PathVariable("id") int id,
                                        HttpMethod method,
                                        HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI("https", authority, "/users/" + id, request.getQueryString(), null);
        return new RestTemplate().exchange(uri, method, new HttpEntity<>(body), User.class);
    }
}
