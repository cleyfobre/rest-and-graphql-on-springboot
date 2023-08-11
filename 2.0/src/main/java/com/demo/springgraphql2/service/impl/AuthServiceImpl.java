package com.demo.springgraphql2.service.impl;

import com.demo.springgraphql2.dto.AuthDto;
import com.demo.springgraphql2.entity.User;
import com.demo.springgraphql2.repository.UserRepository;
import com.demo.springgraphql2.security.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthDto.JwtTokenResponse login(AuthDto.LoginRequest request) {
        try {
            final Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    final String accessToken = jwtTokenUtil.generateAccessToken(user);
                    final String refreshToken = jwtTokenUtil.generateRefreshToken(user);
                    return new AuthDto.JwtTokenResponse("SUCCESS", accessToken, refreshToken);
                }
            }
            return new AuthDto.JwtTokenResponse("FAIL", null, null);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password.", e);
        }
    }
}
