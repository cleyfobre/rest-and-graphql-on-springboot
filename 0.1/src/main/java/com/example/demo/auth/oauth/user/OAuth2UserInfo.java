package com.example.demo.auth.oauth.user;

public interface OAuth2UserInfo {

    String getProvider();
    String getProviderId();
    String getName();
    String getRole();

}
