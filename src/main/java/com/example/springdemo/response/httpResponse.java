package com.example.springdemo.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Data;

@Deprecated
@Data
@Builder
public class httpResponse {

    private int code;
    private String message;

    /**
     * JAVA servlet 提供的方法 弃用
     * 改采用SpringBoot的ResponseEntity
     */
    public httpResponse getResult400(String message) {
        return httpResponse
                .builder()
                .code(HttpServletResponse.SC_UNAUTHORIZED)
                .message(message)
                .build();
    }

}
