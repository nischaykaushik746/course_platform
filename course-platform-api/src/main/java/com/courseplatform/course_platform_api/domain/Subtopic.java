package com.courseplatform.course_platform_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subtopics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subtopic {

    @Id
    @Column(length = 100)
    private String id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    @JsonIgnore
    private Topic topic;
}

