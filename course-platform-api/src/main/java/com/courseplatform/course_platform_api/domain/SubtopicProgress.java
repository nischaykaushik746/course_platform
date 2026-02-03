package com.courseplatform.course_platform_api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(
        name = "subtopic_progress",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "subtopic_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubtopicProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Subtopic subtopic;

    private boolean completed;

    private Instant completedAt;
}

