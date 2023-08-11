package com.demo.springgraphql2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class AuthDto {

    @Data
    @Builder
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class JwtTokenResponse {
        private String message;
        private String accessToken;
        private String refreshToken;
    }

}
