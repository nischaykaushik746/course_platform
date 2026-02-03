package com.courseplatform.course_platform_api.repository;

import com.courseplatform.course_platform_api.domain.SubtopicProgress;
import com.courseplatform.course_platform_api.domain.Subtopic;
import com.courseplatform.course_platform_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubtopicProgressRepository extends JpaRepository<SubtopicProgress, Long> {

    Optional<SubtopicProgress> findByUserAndSubtopic(User user, Subtopic subtopic);

    List<SubtopicProgress> findByUser(User user);
}

