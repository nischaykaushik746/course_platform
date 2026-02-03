package com.courseplatform.course_platform_api.course.search.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResponse {
    private String query;
    private List<CourseSearchResultDto> results;
}

