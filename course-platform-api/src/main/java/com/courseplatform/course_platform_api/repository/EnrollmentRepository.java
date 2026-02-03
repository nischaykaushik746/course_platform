package com.courseplatform.course_platform_api.repository;

import com.courseplatform.course_platform_api.domain.Enrollment;
import com.courseplatform.course_platform_api.domain.Course;
import com.courseplatform.course_platform_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByUserAndCourse(User user, Course course);
}

