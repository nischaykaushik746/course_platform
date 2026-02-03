package com.courseplatform.course_platform_api.enrollment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProgressResponse {
    private Long enrollmentId;
    private String courseId;
    private String courseTitle;
    private int totalSubtopics;
    private int completedSubtopics;
    private double completionPercentage;
    private List<ProgressItemDto> completedItems;
}

