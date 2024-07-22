package io.gongson.g_back.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String providers;
}
