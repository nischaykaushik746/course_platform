package com.courseplatform.course_platform_api.course.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseSummaryDto {
    private String id;
    private String title;
    private String description;
    private int topicCount;
    private int subtopicCount;
}

