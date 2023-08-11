package com.example.demo.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.auth.PrincipalDetails;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    // 인증이나 권한에 대한 요청이 있을 때 이 필터를 탄다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // super.doFilterInternal(request, response, chain);
        String authorizationValue = request.getHeader(JwtProps.HEADER_STRING);

        if (authorizationValue == null || !authorizationValue.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authorizationValue.replace(JwtProps.TOKEN_PREFIX, "");
        String name = JWT.require(Algorithm.HMAC512(JwtProps.SECRET))
                .build()
                .verify(token)
                .getClaim("name")
                .asString();

        if (name != null) {
            Users users = userRepository.findByName(name);
            PrincipalDetails principalDetails = new PrincipalDetails(users);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
