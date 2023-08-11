package com.example.demo;

import com.example.demo.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    @SpringBootTest: Integration
    @WebMvcTest: Only for MVC
*/
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class RestTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Test
    public void login() throws Exception {
        mvc.perform(get("/login"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void createUserWithoutAdminRole() throws Exception {
        mvc.perform(post("/user"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void createUser() throws Exception {
        Users users = new Users();
        users.setName("hello");
        users.setPassword(passwordEncoder.encode("hello"));
        users.setRole("ROLE_USER");
        String content = objectMapper.writeValueAsString(users);
        mvc.perform(post("/user")
            .content(content)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getUsers() throws Exception {
        mvc.perform(get("/users"))
            .andExpect(status().isOk());
    }

    @Test
    public void getUsersWithoutUser() throws Exception {
        mvc.perform(get("/users"))
            .andExpect(status().isUnauthorized());
    }

}
