package com.courseplatform.course_platform_api.repository;

import com.courseplatform.course_platform_api.domain.CompletedSubtopic;
import com.courseplatform.course_platform_api.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompletedSubtopicRepository extends JpaRepository<CompletedSubtopic, Long> {

    Optional<CompletedSubtopic> findByEnrollmentAndSubtopicId(
            Enrollment enrollment, String subtopicId);

    List<CompletedSubtopic> findByEnrollment(Enrollment enrollment);
}
