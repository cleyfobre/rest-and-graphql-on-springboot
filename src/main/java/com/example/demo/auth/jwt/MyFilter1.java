package com.example.demo.auth.jwt;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class MyFilter1 implements Filter {

    // config 에서 FilterRegistrationBean 으로 등록
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      log.info("filter 1");
//      chain.doFilter(request, response);
    }
}
