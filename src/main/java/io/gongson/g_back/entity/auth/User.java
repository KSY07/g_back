package io.gongson.g_back.entity.auth;

import io.gongson.g_back.dto.AuthDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="g_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private long pk;
    private @Getter String userId;
    private String password;
    private @Getter String name;
    private @Getter String email;
    private @Getter String phone;
    private @Getter String providers;
    private @Getter boolean isAdmin;

    public static User toEntity(AuthDTO.SignUp dto) {
        return User.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .providers("")
                .isAdmin(false)
                .build();
    }
}
