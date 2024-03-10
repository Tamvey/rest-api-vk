package com.test.web.service;

import com.test.web.dto.register.RegisterRequest;
import com.test.web.dto.register.UserMapper;
import com.test.web.enitiy.User;
import com.test.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(user);
    }


    public boolean register(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()) != null ||
                userRepository.findByEmail(registerRequest.getUsername()) != null)
            return false;
        userRepository.save(UserMapper.toUser(registerRequest));
        return true;
    }

}
