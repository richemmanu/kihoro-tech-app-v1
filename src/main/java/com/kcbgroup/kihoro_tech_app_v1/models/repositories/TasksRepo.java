package com.kcbgroup.kihoro_tech_app_v1.models.repositories;

import com.kcbgroup.kihoro_tech_app_v1.models.TaskStatusEnum;
import com.kcbgroup.kihoro_tech_app_v1.models.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TasksRepo extends JpaRepository<Tasks, Long> {
    List<Tasks> findByProjectId(Long projectId);

    @Query("SELECT task FROM Tasks task WHERE task.project.id = :projectId " +
            "AND (:status IS NULL OR task.status = :status) " +
            "AND (:dueDate IS NULL OR task.dueDate = :dueDate)")
    List<Tasks> findTasksByProjectIdAndStatusAndDueDate(Long projectId, TaskStatusEnum status, LocalDate dueDate);

    @Query("SELECT task.status, COUNT(task) FROM Tasks task WHERE task.project.id = :projectId GROUP BY task.status")
    Map<TaskStatusEnum, Long> countTasksByProjectIdGroupedByStatus(Long projectId);
}
