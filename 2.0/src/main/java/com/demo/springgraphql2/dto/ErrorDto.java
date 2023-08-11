package com.demo.springgraphql2.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorDto {

    ErrorDto.Response data;

    @Data
    @Builder
    public static class Response {
        private HttpStatus code;
    }


}
