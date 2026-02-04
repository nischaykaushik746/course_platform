package com.courseplatform.course_platform_api.enrollment;

import com.courseplatform.course_platform_api.domain.*;
import com.courseplatform.course_platform_api.enrollment.dto.*;
import com.courseplatform.course_platform_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final EnrollmentRepository enrollmentRepository;
    private final SubtopicRepository subtopicRepository;
    private final SubtopicProgressRepository progressRepository;

    public ProgressItemDto completeSubtopic(User user, String subtopicId) {

        Subtopic subtopic = subtopicRepository.findByIdWithCourse(subtopicId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Subtopic not found"));

        Course course = subtopic.getTopic().getCourse();

        Enrollment enrollment = enrollmentRepository
                .findByUserAndCourse(user, course)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "You must be enrolled in this course"));

        SubtopicProgress progress = progressRepository
                .findByUserAndSubtopic(user, subtopic)
                .orElse(SubtopicProgress.builder()
                        .user(user)
                        .subtopic(subtopic)
                        .completed(true)
                        .completedAt(Instant.now())
                        .build());

        if (!progress.isCompleted()) {
            progress.setCompleted(true);
            progress.setCompletedAt(Instant.now());
        }

        SubtopicProgress saved = progressRepository.save(progress);

        return ProgressItemDto.builder()
                .subtopicId(subtopic.getId())
                .subtopicTitle(subtopic.getTitle())
                .completedAt(saved.getCompletedAt())
                .build();
    }

    public ProgressResponse getProgress(User user, Long enrollmentId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Enrollment not found"));

        if (!enrollment.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Access denied");
        }

        Course course = enrollment.getCourse();

        long totalSubtopics =
                progressRepository.countSubtopicsByCourse(course);

        List<SubtopicProgress> completed =
                progressRepository.findByUserAndCourse(user, course);

        int completedCount = completed.size();

        double percentage = totalSubtopics == 0
                ? 0
                : Math.round(((double) completedCount / totalSubtopics) * 10000) / 100.0;

        List<ProgressItemDto> items = completed.stream()
                .map(p -> ProgressItemDto.builder()
                        .subtopicId(p.getSubtopic().getId())
                        .subtopicTitle(p.getSubtopic().getTitle())
                        .completedAt(p.getCompletedAt())
                        .build())
                .toList();

        return ProgressResponse.builder()
                .enrollmentId(enrollment.getId())
                .courseId(course.getId())
                .courseTitle(course.getTitle())
                .totalSubtopics((int) totalSubtopics)
                .completedSubtopics(completedCount)
                .completionPercentage(percentage)
                .completedItems(items)
                .build();
    }
}
