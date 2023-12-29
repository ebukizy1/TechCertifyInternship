package com.techcertify.techcertify_taskmanagement_application.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.techcertify.techcertify_taskmanagement_application.data.model.TaskStatus.COMPLETED;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String taskDescription;
    @Enumerated(STRING)
    private TaskStatus taskStatus;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime dueDate;
    @ManyToOne
    private User UserTask;

    @PrePersist
    public void setCreatedAt(){
        this.createdAt=LocalDateTime.now();

        if (this.dueDate == null) {
            this.dueDate = LocalDateTime.now().plusDays(7);
        }
    }
    @PreUpdate
    public void setCompletedAtOnUpdate() {
        if (this.taskStatus == COMPLETED) {
            this.completedAt = LocalDateTime.now();
        }
    }

}


