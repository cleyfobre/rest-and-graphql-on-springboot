package com.example.demo.auth;

import com.example.demo.entity.Users;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
@Slf4j
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Users users;
    private Map<String, Object> attributes;

    public PrincipalDetails(Users users) {
        super();
        this.users = users;
    }

    public PrincipalDetails(Users users, Map<String, Object> attributes) {
        super();
        this.users = users;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        log.info("In PrincipalDetails: " + users);
        collection.add((GrantedAuthority) () -> users.getRole());
        return collection;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // OAuth2User Override
    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return null;
//    }

    @Override
    public String getName() {
        return null;
    }
}
