package com.courseplatform.course_platform_api.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    private String email;
    private long expiresIn;
}

