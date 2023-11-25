package com.AuthenticationServer.AutheticationServer.service;



import com.AuthenticationServer.AutheticationServer.entity.UserCredential;
import com.AuthenticationServer.AutheticationServer.model.UserRequest;
import com.AuthenticationServer.AutheticationServer.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public boolean saveUser(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        UserCredential userCredential=UserCredential.builder().email(userRequest.getEmail()).name(userRequest.getName()).password(userRequest.getPassword()).build();

        repository.save(userCredential);

        return true;
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}