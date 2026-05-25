package com.project.ecommerce.controllers;

import com.project.ecommerce.requests.LoginRequest;
import com.project.ecommerce.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest lgnReq){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        lgnReq.email(),
                        lgnReq.password()
                )
        );
        return jwtService.generateToken(lgnReq.email());
    }
}
