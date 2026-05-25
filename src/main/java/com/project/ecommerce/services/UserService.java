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


        UserEntity newUser = new UserEntity(userReq.name(), userReq.email(), passwordEncoder.encode(userReq.password()), RolesEnum.ROLE_USER, pathPhoto);

        userRepository.save(newUser);

        return new UserResponse(newUser.getId(), newUser.getName(), newUser.getEmail());
    }

    //    Método pra ver todos os usuários
    public List<UserResponse> showAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream().map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail())).toList();
    }

    //    Método pro proprio usuario ou admin atualizar os dados
    public UserResponse updateUser(UUID id, UserRequest userReq, MultipartFile photo) throws IOException {
//        pegando o email do usuário autenticado
        String loggedEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity loggedUser = userRepository.findByEmail(loggedEmail).orElseThrow(() -> new UsernameNotFoundException("Usuário autenticado não encontrado"));

//        verificar se é o proprio user ou admin
        if (!loggedUser.getId().equals(id) && !loggedUser.getRole().equals(RolesEnum.ROLE_ADMIN)) {
            throw new AccessDeniedException("você não pode atualizar esses dados de outro usuário");
        }

        UserEntity userToUpdate = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("usuário não encontrado")));

        userToUpdate.setName(userReq.name());
        userToUpdate.setPassword(passwordEncoder.encode(userReq.password()));

        if (photo != null && !photo.isEmpty()) {
            String pathPhoto = photoService.savePhoto(photo);
            userToUpdate.setPhoto(pathPhoto);
        }

        userRepository.save(userToUpdate);

        return new UserResponse(userToUpdate.getId(), userToUpdate.getName(), userToUpdate.getEmail());
    }
}
