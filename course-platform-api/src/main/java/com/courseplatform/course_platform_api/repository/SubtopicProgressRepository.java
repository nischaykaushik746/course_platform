package com.courseplatform.course_platform_api.repository;

import com.courseplatform.course_platform_api.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubtopicProgressRepository
        extends JpaRepository<SubtopicProgress, Long> {

    Optional<SubtopicProgress> findByUserAndSubtopic(User user, Subtopic subtopic);

    @Query("""
        select sp
        from SubtopicProgress sp
        where sp.user = :user
        and sp.subtopic.topic.course = :course
    """)
    List<SubtopicProgress> findByUserAndCourse(User user, Course course);

    @Query("""
        select count(st)
        from Subtopic st
        where st.topic.course = :course
    """)
    long countSubtopicsByCourse(Course course);
}
