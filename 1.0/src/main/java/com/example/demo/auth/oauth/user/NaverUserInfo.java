package com.example.demo.auth.oauth.user;

import lombok.Data;

import java.util.Map;

@Data
public class NaverUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return (String) ((Map) attributes.get("response")).get("id");
    }

    @Override
    public String getName() {
        return "naver_" + ((Map) attributes.get("response")).get("id");
    }

    @Override
    public String getRole() {
        return "ROLE_USER";
    }
}
