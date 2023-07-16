package com.hitesh.springboottemplate.controller;

import com.hitesh.springboottemplate.entities.Users;
import com.hitesh.springboottemplate.modals.JWTRequest;
import com.hitesh.springboottemplate.modals.JWTResponse;
import com.hitesh.springboottemplate.repository.UserRepository;
import com.hitesh.springboottemplate.security.JwtHelper;
import com.hitesh.springboottemplate.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JWTResponse response = JWTResponse
                .builder()
                .token(token)
                .message("Login Success!!")
                .responseCode(HttpStatus.OK.toString())
                .email(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<JWTResponse> RegisterUser(@RequestBody Users user){
        // add check for username exists in a DB
        if(userRepository.existsUsersByUsername(user.getUsername())){
            JWTResponse response = JWTResponse.builder().message("Username is already taken!").responseCode(HttpStatus.BAD_REQUEST.toString()
            ).token("").email(user.getUsername()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        final UserDetails userDetails = userServices.createUser(user);;
        final String token = helper.generateToken(userDetails);

        JWTResponse response = JWTResponse
                .builder()
                .token(token)
                .email(user.getUsername())
                .message("User Registered Successfully")
                .responseCode(HttpStatus.OK.toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
