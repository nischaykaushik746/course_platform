package com.courseplatform.course_platform_api.repository;

import com.courseplatform.course_platform_api.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("""
        select distinct c from Course c
        left join c.topics t
        left join t.subtopics s
        where
        lower(c.title) like lower(concat('%', :q, '%')) or
        lower(c.description) like lower(concat('%', :q, '%')) or
        lower(t.title) like lower(concat('%', :q, '%')) or
        lower(s.title) like lower(concat('%', :q, '%')) or
        lower(s.content) like lower(concat('%', :q, '%'))
    """)
    List<Course> searchCourses(@Param("q") String query);
}

