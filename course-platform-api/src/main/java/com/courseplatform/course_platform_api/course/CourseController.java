package com.courseplatform.course_platform_api.course;

import com.courseplatform.course_platform_api.course.dto.CourseDetailDto;
import com.courseplatform.course_platform_api.course.dto.CourseListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public CourseListResponse getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public CourseDetailDto getCourseById(@PathVariable String courseId) {
        return courseService.getCourseById(courseId);
    }
}
