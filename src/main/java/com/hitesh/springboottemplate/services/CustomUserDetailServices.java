package com.hitesh.springboottemplate.services;

import com.hitesh.springboottemplate.entities.Users;
import com.hitesh.springboottemplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        //Load user from Database
        Users users = (Users) userRepository.findByUsername(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        return users;
    }
}
