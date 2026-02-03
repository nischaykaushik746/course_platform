package com.courseplatform.course_platform_api.seed.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeedRootDto {
    private List<SeedCourseDto> courses;
}

