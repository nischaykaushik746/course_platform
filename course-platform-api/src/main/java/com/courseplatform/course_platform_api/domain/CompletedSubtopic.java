package com.courseplatform.course_platform_api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"enrollment_id", "subtopic_id"})
)
public class CompletedSubtopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subtopicId;

    private Instant completedAt;

    @ManyToOne(optional = false)
    private Enrollment enrollment;
}
