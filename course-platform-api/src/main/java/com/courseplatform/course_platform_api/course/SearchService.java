package com.courseplatform.course_platform_api.course;

import com.courseplatform.course_platform_api.course.search.dto.*;
import com.courseplatform.course_platform_api.domain.Course;
import com.courseplatform.course_platform_api.domain.Subtopic;
import com.courseplatform.course_platform_api.domain.Topic;
import com.courseplatform.course_platform_api.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final CourseRepository courseRepository;

    public SearchResponse search(String query) {

        List<Course> courses = courseRepository.searchCourses(query);
        List<CourseSearchResultDto> results = new ArrayList<>();

        for (Course course : courses) {

            List<SearchMatchDto> matches = new ArrayList<>();

            // Course-level match
            if (contains(course.getTitle(), query) ||
                    contains(course.getDescription(), query)) {

                matches.add(SearchMatchDto.builder()
                        .type("course")
                        .snippet(course.getDescription())
                        .build());
            }

            for (Topic topic : course.getTopics()) {

                if (contains(topic.getTitle(), query)) {
                    matches.add(SearchMatchDto.builder()
                            .type("topic")
                            .topicTitle(topic.getTitle())
                            .snippet(topic.getTitle())
                            .build());
                }

                for (Subtopic subtopic : topic.getSubtopics()) {

                    if (contains(subtopic.getTitle(), query)) {
                        matches.add(buildSubtopicMatch("subtopic", topic, subtopic));
                    }

                    if (contains(subtopic.getContent(), query)) {
                        matches.add(buildSubtopicMatch("content", topic, subtopic));
                    }
                }
            }

            results.add(CourseSearchResultDto.builder()
                    .courseId(course.getId())
                    .courseTitle(course.getTitle())
                    .matches(matches)
                    .build());
        }

        return SearchResponse.builder()
                .query(query)
                .results(results)
                .build();
    }

    private boolean contains(String text, String q) {
        return text != null && text.toLowerCase().contains(q.toLowerCase());
    }

    private SearchMatchDto buildSubtopicMatch(String type, Topic topic, Subtopic subtopic) {
        return SearchMatchDto.builder()
                .type(type)
                .topicTitle(topic.getTitle())
                .subtopicId(subtopic.getId())
                .subtopicTitle(subtopic.getTitle())
                .snippet(extractSnippet(subtopic.getContent()))
                .build();
    }

    private String extractSnippet(String content) {
        if (content == null) return "";
        return content.length() > 120
                ? content.substring(0, 120) + "..."
                : content;
    }
}

