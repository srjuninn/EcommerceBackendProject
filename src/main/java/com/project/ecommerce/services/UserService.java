package com.project.ecommerce.services;

import com.project.ecommerce.entities.UserEntity;
import com.project.ecommerce.enums.RolesEnum;
import com.project.ecommerce.repositories.UserRepository;
import com.project.ecommerce.requests.UserRequest;
import com.project.ecommerce.responses.UserResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PhotoService photoService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PhotoService photoService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.photoService = photoService;
    }

    //    Método pra criar o usuário
    public UserResponse createUser(UserRequest userReq, MultipartFile photo) throws IOException {
        if (userReq == null) {
            throw new IllegalArgumentException("os dados inseridos são inválidos");
        }
        if (userRepository.findByEmail(userReq.email()).isPresent()) {
            throw new DuplicateKeyException("já existe um usuário com esse email cadastrado");
        }
        String pathPhoto = photoService.savePhoto(photo);


        UserEntity newUser = new UserEntity(userReq.name(), userReq.email(), passwordEncoder.encode(userReq.password()), RolesEnum.ROLE_USER, userReq.photo());

        userRepository.save(newUser);

        return new UserResponse(newUser.getId(), newUser.getName(), newUser.getEmail());
    }

//    Método pra ver todos os usuários
    public List<UserResponse> showAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream().map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail())).toList();
    }


}
