package com.courseplatform.course_platform_api.exception;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ErrorResponse {
    private String error;
    private String message;
    private Instant timestamp;
}

