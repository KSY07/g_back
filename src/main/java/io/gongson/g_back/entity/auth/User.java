package io.gongson.g_back.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
    @Enumerated(EnumType.STRING)
    private UserType type;
    private String name;
    private String email;
    private String phone;

}
