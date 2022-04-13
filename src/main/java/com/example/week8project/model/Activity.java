package com.example.week8project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;
    @Column(nullable = false)
    private String activityName;
    @Column(nullable = false)
    private String activityDescription;
    @Column(nullable = false)
    private String activityStatus;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private LocalTime completedAt;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "activities", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<User> users;

    public Activity(String activityName, String activityDescription, String activityStatus, LocalDateTime createdAt, LocalDateTime updatedAt, LocalTime completedAt) {
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.activityStatus = activityStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.completedAt = completedAt;
    }
}