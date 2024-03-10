package com.test.web.dto.register;

import com.test.web.enitiy.User;

public class UserMapper {
    public static User toUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setAuthority(registerRequest.getAuthority());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setUsername(registerRequest.getUsername());
        user.setEnabled(registerRequest.getEnabled());
        return user;
    }
}
