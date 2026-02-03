package com.courseplatform.course_platform_api.enrollment;

import com.courseplatform.course_platform_api.domain.User;
import com.courseplatform.course_platform_api.enrollment.dto.EnrollmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/{courseId}/enroll")
    public EnrollmentResponse enroll(
            @AuthenticationPrincipal User user,
            @PathVariable String courseId) {

        return enrollmentService.enroll(user, courseId);
    }
}

