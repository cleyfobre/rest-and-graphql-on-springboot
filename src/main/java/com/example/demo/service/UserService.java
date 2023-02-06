package com.example.demo.service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public List<Users> getUsers(String name) {
        if (name.isBlank()) {
            return userRepository.findAll();
        } else {
            return userRepository.findFirst2ByNameLikeOrderByIdDesc(name);
        }
    }

    public Users getUser(String name) {
        return userRepository.findByName(name);
    }

    public Users createUsers(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

}
