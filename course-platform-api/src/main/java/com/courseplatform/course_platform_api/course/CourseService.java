package com.courseplatform.course_platform_api.course;

import com.courseplatform.course_platform_api.course.dto.*;
import com.courseplatform.course_platform_api.domain.Course;
import com.courseplatform.course_platform_api.domain.Topic;
import com.courseplatform.course_platform_api.domain.Subtopic;
import com.courseplatform.course_platform_api.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseListResponse getAllCourses() {
        List<CourseSummaryDto> summaries = courseRepository.findAll()
                .stream()
                .map(this::toSummaryDto)
                .toList();

        return CourseListResponse.builder()
                .courses(summaries)
                .build();
    }

    public CourseDetailDto getCourseById(String courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return toDetailDto(course);
    }

    private CourseSummaryDto toSummaryDto(Course course) {
        int topicCount = course.getTopics().size();
        int subtopicCount = course.getTopics()
                .stream()
                .mapToInt(t -> t.getSubtopics().size())
                .sum();

        return CourseSummaryDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .topicCount(topicCount)
                .subtopicCount(subtopicCount)
                .build();
    }

    private CourseDetailDto toDetailDto(Course course) {
        List<TopicDto> topics = course.getTopics().stream()
                .map(this::toTopicDto)
                .toList();

        return CourseDetailDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .topics(topics)
                .build();
    }

    private TopicDto toTopicDto(Topic topic) {
        List<SubtopicDto> subtopics = topic.getSubtopics().stream()
                .map(this::toSubtopicDto)
                .toList();

        return TopicDto.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .subtopics(subtopics)
                .build();
    }

    private SubtopicDto toSubtopicDto(Subtopic subtopic) {
        return SubtopicDto.builder()
                .id(subtopic.getId())
                .title(subtopic.getTitle())
                .content(subtopic.getContent())
                .build();
    }
}

