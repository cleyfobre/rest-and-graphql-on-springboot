package com.example.demo.auth.oauth.user;

import lombok.Data;

import java.util.Map;

@Data
public class FacebookUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public FacebookUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "facebook";
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getName() {
        return "facebook_" + attributes.get("id");
    }

    @Override
    public String getRole() {
        return "ROLE_USER";
    }
}
