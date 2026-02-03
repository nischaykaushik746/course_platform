package com.courseplatform.course_platform_api.seed.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeedTopicDto {
    private String id;
    private String title;
    private List<SeedSubtopicDto> subtopics;
}

