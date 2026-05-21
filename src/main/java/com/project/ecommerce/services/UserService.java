package com.project.ecommerce.services;

import com.project.ecommerce.entities.UserEntity;
import com.project.ecommerce.enums.RolesEnum;
import com.project.ecommerce.repositories.UserRepository;
import com.project.ecommerce.requests.UserRequest;
import com.project.ecommerce.responses.UserResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //    Método pra criar o usuário
    public UserResponse createUser(UserRequest userReq) {
        if (userReq == null) {
            throw new IllegalArgumentException("os dados inseridos são inválidos");
        }
        if (userRepository.findByEmail(userReq.email()).isPresent()) {
            throw new DuplicateKeyException("já existe um usuário com esse email cadastrado");
        }
        UserEntity newUser = new UserEntity(userReq.name(), userReq.email(), passwordEncoder.encode(userReq.password()), RolesEnum.ROLE_USER);

        userRepository.save(newUser);

        return new UserResponse(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getRole());
    }
}
