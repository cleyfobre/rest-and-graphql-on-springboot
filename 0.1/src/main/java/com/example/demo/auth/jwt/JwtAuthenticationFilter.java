package com.example.demo.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.auth.PrincipalDetails;
import com.example.demo.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // /login 실행을 위해 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Users users = mapper.readValue(request.getInputStream(), Users.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(users.getName(), users.getPassword());

            // authenticationManager 가 로그인 시도를 하면
            // printcipalDetailsService가 호출하여 loadUserByUsername() 실행함
            Authentication authentication = authenticationManager.authenticate(token);
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            return authentication;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 인증 완료되면 어차피 여기로 옴
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProps.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getUsers().getId())
                .withClaim("name", principalDetails.getUsers().getName())
                .sign(Algorithm.HMAC512(JwtProps.SECRET));

        response.addHeader(JwtProps.HEADER_STRING, JwtProps.TOKEN_PREFIX + jwtToken);
    }

}
