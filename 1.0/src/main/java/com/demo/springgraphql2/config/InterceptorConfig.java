package com.demo.springgraphql2.config;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

//@Configuration
public class InterceptorConfig implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        return Mono.delay(Duration.ofMillis(10)).flatMap(aLong -> chain.next(request));
    }

}
