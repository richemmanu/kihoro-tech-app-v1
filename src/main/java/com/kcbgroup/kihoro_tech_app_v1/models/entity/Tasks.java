package com.kcbgroup.kihoro_tech_app_v1.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kcbgroup.kihoro_tech_app_v1.models.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "APP_PROJECT_TASKS")
@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "TASK_TITLE")
    private String title;

    @Column(name = "TASK_DESC")
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @NotNull(message="Due Date is a required item")
    @PastOrPresent(message = "Provide a valid due date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;
}
