package io.gongson.g_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class SignIn {
        private String userId;
        private String password;
        private String userType;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class SignUp {
        private String userId;
        private String password;
        private String nickname;
        private String email;
        private String phone;
    }

}
