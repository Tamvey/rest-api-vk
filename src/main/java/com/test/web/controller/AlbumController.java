package com.test.web.controller;

import com.test.web.dto.album.Album;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
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
public class AlbumController {
    @Value("${authority}")
    private String authority;

    @Autowired
    ApplicationContext context;

    @Cacheable(value = "albums", key="#request.getRequestURI()")
    @GetMapping("/api/albums")
    public ResponseEntity<List> getAll(@RequestBody(required = false) Album body,
                                       HttpMethod method,
                                       HttpServletRequest request)
            throws URISyntaxException {

        URI uri = new URI("https", authority, "/albums", request.getQueryString(), null);
        return new RestTemplate().exchange(uri, method, new HttpEntity<>(body), List.class);
    }

    @Cacheable(value = "albums", key="#id")
    @GetMapping("/api/albums/{id}")
    public ResponseEntity<Album> getOne(@PathVariable("id") int id,
                                        HttpServletRequest request)
            throws URISyntaxException {
        URI uri = new URI("https", authority, "/albums/" + id, request.getQueryString(), null);
        return new ResponseEntity<>(new RestTemplate().getForObject(uri, Album.class), HttpStatus.OK);
    }

    @CachePut(value = "albums", key="#id")
    @PostMapping("/api/albums")
    public ResponseEntity<Album> newAlbum(@RequestBody Album body,
                                          HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI("https", authority, "/albums", request.getQueryString(), null);
        return new ResponseEntity<>(new RestTemplate().postForObject(uri, body, Album.class), HttpStatus.CREATED);
    }

    @CachePut(value = "albums", key="#id")
    @PutMapping("/api/albums/{id}")
    public ResponseEntity<Album> updateAlbum(@RequestBody Album body,
                                             @PathVariable("id") int id,
                                             HttpMethod method,
                                             HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI("https", authority, "/albums/" + id, request.getQueryString(), null);
        new RestTemplate().put(uri, body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @CacheEvict(value = "albums", key="#id")
    @DeleteMapping("/api/albums/{id}")
    public ResponseEntity<Album> deleteAlbum(@RequestBody(required = false) Album body,
                                             @PathVariable("id") int id,
                                             HttpMethod method,
                                             HttpServletRequest request) throws URISyntaxException {
        URI uri = new URI("https", authority, "/albums/" + id, request.getQueryString(), null);
        return new RestTemplate().exchange(uri, method, new HttpEntity<>(body), Album.class);
    }
}
