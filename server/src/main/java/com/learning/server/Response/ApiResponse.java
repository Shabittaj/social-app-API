package com.learning.server.Response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String message;

    private Boolean response;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", response=" + response +
                '}';
    }
}
