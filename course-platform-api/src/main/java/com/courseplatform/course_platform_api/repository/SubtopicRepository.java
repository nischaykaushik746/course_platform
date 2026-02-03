package com.courseplatform.course_platform_api.repository;

import com.courseplatform.course_platform_api.domain.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtopicRepository extends JpaRepository<Subtopic, String> {
}

