package com.courseplatform.course_platform_api.repository;

import com.courseplatform.course_platform_api.domain.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubtopicRepository extends JpaRepository<Subtopic, String> {

    @Query("""
        select s
        from Subtopic s
        join fetch s.topic t
        join fetch t.course
        where s.id = :id
    """)
    Optional<Subtopic> findByIdWithCourse(String id);
}


