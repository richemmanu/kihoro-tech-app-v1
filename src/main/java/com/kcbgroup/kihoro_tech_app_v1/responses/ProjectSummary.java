package com.kcbgroup.kihoro_tech_app_v1.responses;

import com.kcbgroup.kihoro_tech_app_v1.models.TaskStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class ProjectSummary {
    private Long projectId;
    private String projectName;
    private Map<TaskStatusEnum, Long> tasksCountByStatus;
}
