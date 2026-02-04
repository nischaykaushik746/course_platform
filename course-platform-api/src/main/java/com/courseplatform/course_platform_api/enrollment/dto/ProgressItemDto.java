package com.courseplatform.course_platform_api.enrollment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ProgressItemDto {
    private String subtopicId;
    private String subtopicTitle;
    private Instant completedAt;
}
