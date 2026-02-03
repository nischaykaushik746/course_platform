package com.courseplatform.course_platform_api.course.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubtopicDto {
    private String id;
    private String title;
    private String content;
}

