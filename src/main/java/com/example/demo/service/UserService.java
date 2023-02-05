package com.example.demo.service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

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
        return userRepository.save(users);
    }

}
