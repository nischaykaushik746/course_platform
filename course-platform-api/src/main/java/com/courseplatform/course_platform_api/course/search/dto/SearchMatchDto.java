package com.courseplatform.course_platform_api.course.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchMatchDto {
    private String type;
    private String topicTitle;
    private String subtopicId;
    private String subtopicTitle;
    private String snippet;
}

