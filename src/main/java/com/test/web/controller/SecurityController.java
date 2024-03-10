package com.test.web.controller;


import com.test.web.dto.register.RegisterRequest;
import com.test.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (userService.register(registerRequest))
            return new ResponseEntity<>("Register success", HttpStatus.OK);
        return new ResponseEntity<>("Register failed", HttpStatus.BAD_REQUEST);
    }


}
