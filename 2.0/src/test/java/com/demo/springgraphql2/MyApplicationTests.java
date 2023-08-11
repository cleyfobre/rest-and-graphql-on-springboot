package com.demo.springgraphql2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static graphql.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Slf4j
public class MyApplicationTests {

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void passwordEncode() {
        // given
        String rawPassword = "123";

        // when
        String encodedPassword = passwordEncoder.encode(rawPassword);

        log.info("rawPassword: {}, encodedPassword: {}", rawPassword, encodedPassword);
        log.info("matches: {}", passwordEncoder.matches(rawPassword, encodedPassword));

        // then
        assertAll(
                () -> assertNotEquals(rawPassword, encodedPassword),
                () -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
        );
    }

}
