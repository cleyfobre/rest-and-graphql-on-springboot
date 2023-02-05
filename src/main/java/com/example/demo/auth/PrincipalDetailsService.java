package com.example.demo.auth;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// SecurityConfig 에서 .loginProcessingUrl("/login.do") 이 부분이 호출되면
// UserDetailsService 를 구현한 클래스의 loadUserByUsername 함수가 IoC 되어 있다.
@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByName(username);
        log.info("users: " + users);
        if (users != null) return new PrincipalDetails(users);
        return null;
    }

}