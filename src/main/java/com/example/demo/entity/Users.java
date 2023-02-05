package com.example.demo.entity;

import com.example.demo.input.UsersInput;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String role;
    private String provider;
    @Column(name = "provider_id")
    private String providerId;

    public static Users from(UsersInput input) {
        return new Users(input.getName(), input.getPassword(), input.getRole());
    }

}
