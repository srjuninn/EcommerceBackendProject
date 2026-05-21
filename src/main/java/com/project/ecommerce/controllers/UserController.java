package com.project.ecommerce.controllers;

import com.project.ecommerce.requests.UserRequest;
import com.project.ecommerce.responses.UserResponse;
import com.project.ecommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userReq) {
        UserResponse userRes = userService.createUser(userReq);
        URI location = URI.create("/users/" + userRes.id());
        return ResponseEntity.created(location).body(userRes);

    }
}
