package com.demo.springgraphql2.service;

import com.demo.springgraphql2.dto.AuthDto;

public interface AuthService {

    AuthDto.JwtTokenResponse login(AuthDto.LoginRequest request);

}
