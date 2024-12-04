package com.kcbgroup.kihoro_tech_app_v1.models.requests;

import com.kcbgroup.kihoro_tech_app_v1.models.TaskStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Setter
@Getter
@AllArgsConstructor
public class TasksReq {
    @NotBlank(message = "Task title is required")
    @Size(max = 100)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @NotNull(message="Due Date is a required item")
    @PastOrPresent(message = "Provide a valid due date")
    private LocalDate dueDate;
}
