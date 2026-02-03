package com.courseplatform.course_platform_api.repository;

import com.courseplatform.course_platform_api.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> {
}

