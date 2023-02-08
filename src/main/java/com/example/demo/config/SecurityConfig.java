package com.example.demo.config;

import com.example.demo.auth.jwt.JwtAuthenticationFilter;
import com.example.demo.auth.jwt.JwtAuthorizationFilter;
import com.example.demo.auth.jwt.MyFilter1;
import com.example.demo.auth.jwt.MyFilter2;
import com.example.demo.auth.oauth.PrincipalOauth2UserService;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private final UserRepository userRepository;
    private final PrincipalOauth2UserService principalOauth2UserService;

    // v1. form login and oauth2
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//            .anyRequest().permitAll()
//            .and()
//            .formLogin()
//            .loginPage("/login") // go to login page for unauthorized. ref. PrincipalDetails.java
//            .usernameParameter("name") // tag name in html is 'name'
//            .loginProcessingUrl("/login.do") // catch if login url
//            .defaultSuccessUrl("/") // go to '/' when success login
//            .and()
//            .oauth2Login()
//            .loginPage("/login")
//            .userInfoEndpoint()
//            .userService(principalOauth2UserService);
//
//        return http.build();
//    }

    // v2. jwt
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public FilterRegistrationBean<MyFilter1> myFilter() {
//        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
//        bean.addUrlPatterns("/*");
//        bean.setOrder(0);
//        return bean;
//    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // json 응답을 받을 수 있다.
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        src.registerCorsConfiguration("/v2/**", config);
        return new CorsFilter(src);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/test").permitAll()
            .and()
            .formLogin().disable() // form tag 를 이용한 login 안한다.
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(corsFilter())
//            .addFilterBefore(new MyFilter2(), SecurityContextPersistenceFilter.class)
            .addFilter(new JwtAuthenticationFilter(authenticationManager(
                http.getSharedObject(AuthenticationConfiguration.class))))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(
                http.getSharedObject(AuthenticationConfiguration.class)), userRepository));
            // security filter 순서 참고 : https://gngsn.tistory.com/160

        return http.build();
    }



}
