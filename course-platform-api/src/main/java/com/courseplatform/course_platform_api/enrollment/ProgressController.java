package com.courseplatform.course_platform_api.enrollment;

import com.courseplatform.course_platform_api.domain.User;
import com.courseplatform.course_platform_api.enrollment.dto.ProgressItemDto;
import com.courseplatform.course_platform_api.enrollment.dto.ProgressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/subtopics/{subtopicId}/complete")
    public ProgressItemDto complete(
            @AuthenticationPrincipal User user,
            @PathVariable String subtopicId) {

        return progressService.completeSubtopic(user, subtopicId);
    }

    @GetMapping("/enrollments/{enrollmentId}/progress")
    public ProgressResponse progress(
            @AuthenticationPrincipal User user,
            @PathVariable Long enrollmentId) {

        return progressService.getProgress(user, enrollmentId);
    }
}

