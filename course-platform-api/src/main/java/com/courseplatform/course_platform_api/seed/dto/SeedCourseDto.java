package com.courseplatform.course_platform_api.seed.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeedCourseDto {
    private String id;
    private String title;
    private String description;
    private List<SeedTopicDto> topics;
}

