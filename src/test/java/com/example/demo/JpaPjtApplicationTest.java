package com.example.demo;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@Slf4j
public class JpaPjtApplicationTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void insertTestData() {
        Users u1 = new Users();
        u1.setName("HM Son");
        userRepository.save(u1);
        Users u2 = new Users();
        u2.setName("H Kane");
        userRepository.save(u2);
        Users u3 = new Users();
        u3.setName("KI Lee");
        userRepository.save(u3);
        Users u4 = new Users();
        u4.setName("JH Son");
        userRepository.save(u4);
    }

    @Test
    void findAllTest() {
        List<Users> usersList = userRepository.findAll();
        for (Users u: usersList) {
            log.info("User Info: " + u.getId() + " " + u.getName());
        }
    }

    @Test
    void find2ByName() {
        List<Users> usersList = userRepository.findFirst2ByNameLikeOrderByIdDesc("%Son");
        for (Users u: usersList) {
            log.info("User Info: " + u.getId() + " " + u.getName());
        }
    }



}
