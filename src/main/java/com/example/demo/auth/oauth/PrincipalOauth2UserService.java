package com.example.demo.auth.oauth;

import com.example.demo.auth.PrincipalDetails;
import com.example.demo.auth.oauth.user.FacebookUserInfo;
import com.example.demo.auth.oauth.user.GoogleUserInfo;
import com.example.demo.auth.oauth.user.NaverUserInfo;
import com.example.demo.auth.oauth.user.OAuth2UserInfo;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Service
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    // google login 후처리
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("Registration: " + userRequest.getClientRegistration());
        Map<String, Object> oAuth2Attributes = super.loadUser(userRequest).getAttributes();
        OAuth2UserInfo userInfo;
        switch (userRequest.getClientRegistration().getRegistrationId()) {
            case "google":
                userInfo = new GoogleUserInfo(oAuth2Attributes);
                break;
            case "facebook":
                userInfo = new FacebookUserInfo(oAuth2Attributes);
                break;
            case "naver":
                userInfo = new NaverUserInfo(oAuth2Attributes);
                break;
            default:
                throw new OAuth2AuthenticationException("Only Google/Facebook supported");
        }
        Users users = userRepository.findByName(userInfo.getName());
        if (users == null) {
            users = Users.builder()
                    .provider(userInfo.getProvider())
                    .providerId(userInfo.getProviderId())
                    .name(userInfo.getName())
                    .password("")
                    .role(userInfo.getRole())
                    .build();
            userRepository.save(users);
        }
        return new PrincipalDetails(users, oAuth2Attributes);
    }

}
