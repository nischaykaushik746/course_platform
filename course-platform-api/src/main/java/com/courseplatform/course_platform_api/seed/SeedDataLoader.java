package com.courseplatform.course_platform_api.seed;

import com.courseplatform.course_platform_api.domain.Course;
import com.courseplatform.course_platform_api.domain.Topic;
import com.courseplatform.course_platform_api.domain.Subtopic;
import com.courseplatform.course_platform_api.repository.CourseRepository;
import com.courseplatform.course_platform_api.seed.dto.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeedDataLoader {

    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void loadSeedData() {
        if (courseRepository.count() > 0) {
            return;
        }

        try {
            InputStream inputStream =
                    new ClassPathResource("data/courses.json").getInputStream();

            SeedRootDto root =
                    objectMapper.readValue(inputStream, SeedRootDto.class);

            List<Course> courses = new ArrayList<>();

            for (SeedCourseDto courseDto : root.getCourses()) {

                Course course = Course.builder()
                        .id(courseDto.getId())
                        .title(courseDto.getTitle())
                        .description(courseDto.getDescription())
                        .topics(new ArrayList<>())
                        .build();

                for (SeedTopicDto topicDto : courseDto.getTopics()) {

                    Topic topic = Topic.builder()
                            .id(topicDto.getId())
                            .title(topicDto.getTitle())
                            .course(course)
                            .subtopics(new ArrayList<>())
                            .build();

                    for (SeedSubtopicDto subtopicDto : topicDto.getSubtopics()) {

                        Subtopic subtopic = Subtopic.builder()
                                .id(subtopicDto.getId())
                                .title(subtopicDto.getTitle())
                                .content(subtopicDto.getContent())
                                .topic(topic)
                                .build();

                        topic.getSubtopics().add(subtopic);
                    }

                    course.getTopics().add(topic);
                }

                courses.add(course);
            }

            courseRepository.saveAll(courses);

            System.out.println("Seed data loaded successfully");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load seed data", e);
        }
    }
}

