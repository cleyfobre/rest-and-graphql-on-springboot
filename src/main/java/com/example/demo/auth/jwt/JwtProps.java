package com.example.demo.auth.jwt;

public class JwtProps {
    public static String SECRET = "JangYoungmin"; // 우리 서버만 알고 있는 비밀값
    public static int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
    public static String TOKEN_PREFIX = "Bearer ";
    public static String HEADER_STRING = "Authorization";
}
