package com.AuthenticationServer.AutheticationServer.controller;



import com.AuthenticationServer.AutheticationServer.model.AuthRequest;
import com.AuthenticationServer.AutheticationServer.model.UserRequest;
import com.AuthenticationServer.AutheticationServer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserRequest userRequest) {

        System.out.println(service.generateToken(userRequest.getEmail()));

       service.saveUser(userRequest);

       return "user added succesfully";
    }

    @PostMapping("/token")
    public String loginUser(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getEmail());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}