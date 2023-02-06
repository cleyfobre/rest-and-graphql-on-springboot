package com.example.demo.resolver;

import com.example.demo.entity.Users;
import com.example.demo.input.UsersInput;
import com.example.demo.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final UserService userService;

    public Users createUsers(UsersInput input) {
        return userService.createUsers(Users.from(input));
    }

    public List<Users> getUsers(String name) {
        return userService.getUsers(name);
    }

    public Users getUser(String name) {
        return userService.getUser(name);
    }

}
