package com.project.ecommerce.entities;

import com.project.ecommerce.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    public UserEntity(String name, String email, String password, RolesEnum role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
