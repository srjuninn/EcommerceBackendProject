package com.project.ecommerce.controllers;

import com.project.ecommerce.requests.UserRequest;
import com.project.ecommerce.responses.UserResponse;
import com.project.ecommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
//  POST
    @PostMapping(consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponse> createUser(
            @Valid
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam MultipartFile photo) throws IOException  {
        UserRequest userReq = new UserRequest(name, email, password, null);
        UserResponse userRes = userService.createUser(userReq, photo);
        URI location = URI.create("/users/" + userRes.id());
        return ResponseEntity.created(location).body(userRes);
    }
//    GET
    @GetMapping
    public ResponseEntity<List<UserResponse>> showAllUsers(){
        List<UserResponse> users = userService.showAllUsers();
        return ResponseEntity.ok(users);
    }

//    PUT
    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponse> updateUser(
            @Valid
            @PathVariable UUID id,
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam MultipartFile photo) throws IOException{
        UserRequest userReq = new UserRequest(name, null, password, null);
        UserResponse updatedUser = userService.updateUser(id, userReq, photo);
        return ResponseEntity.ok(updatedUser);
    }
}
