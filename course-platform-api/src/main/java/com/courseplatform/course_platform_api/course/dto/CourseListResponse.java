package com.courseplatform.course_platform_api.course.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CourseListResponse {
    private List<CourseSummaryDto> courses;
}

