package com.example.demo.auth.oauth.user;

import lombok.Data;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import java.util.Map;

@Data
public class GoogleUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return "google_" + attributes.get("sub");
    }

    @Override
    public String getRole() {
        return "ROLE_USER";
    }
}
