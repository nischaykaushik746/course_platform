package com.courseplatform.course_platform_api.course.search.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CourseSearchResultDto {
    private String courseId;
    private String courseTitle;
    private List<SearchMatchDto> matches;
}

