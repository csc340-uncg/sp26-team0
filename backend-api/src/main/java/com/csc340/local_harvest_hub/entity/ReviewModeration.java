package com.csc340.local_harvest_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "review_moderations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewModeration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moderationId;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private SysAdmin admin;

    @Column(nullable = false)
    private String action;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
