package com.courseplatform.course_platform_api.enrollment;

import com.courseplatform.course_platform_api.domain.Course;
import com.courseplatform.course_platform_api.domain.Enrollment;
import com.courseplatform.course_platform_api.domain.User;
import com.courseplatform.course_platform_api.enrollment.dto.EnrollmentResponse;
import com.courseplatform.course_platform_api.repository.CourseRepository;
import com.courseplatform.course_platform_api.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentResponse enroll(User user, String courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"));

        enrollmentRepository.findByUserAndCourse(user, course)
                .ifPresent(e -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT, "Already enrolled");
                });

        Enrollment enrollment = Enrollment.builder()
                .user(user)
                .course(course)
                .enrolledAt(Instant.now())
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);

        return EnrollmentResponse.builder()
                .enrollmentId(saved.getId())
                .courseId(course.getId())
                .courseTitle(course.getTitle())
                .enrolledAt(saved.getEnrolledAt())
                .build();
    }
}

