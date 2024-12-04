package com.kcbgroup.kihoro_tech_app_v1.services;

import com.kcbgroup.kihoro_tech_app_v1.models.TaskStatusEnum;
import com.kcbgroup.kihoro_tech_app_v1.models.entity.Tasks;
import com.kcbgroup.kihoro_tech_app_v1.models.repositories.TasksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    @Autowired
    TasksRepo tasksRepo;

    public Tasks saveProjectTask(Tasks task)
    {
        return tasksRepo.save(task);
    }

    public Optional<Tasks> getTasksById(Long taskId) {
        return tasksRepo.findById(taskId);
    }

    public void deleteTasksById(Tasks task) {
        tasksRepo.delete(task);
    }

    public List<Tasks> getTasksByProjectId(Long projectId) {
        return tasksRepo.findByProjectId(projectId);
    }

    public List<Tasks> getAllTasks() {
        return tasksRepo.findAll();
    }

    public List<Tasks> getTasksByProjectIdAndFilters(Long projectId, Optional<String> status, Optional<LocalDate> dueDate) {
        TaskStatusEnum taskStatus = status.isPresent() ? TaskStatusEnum.valueOf(status.get().toUpperCase()) : null;
        return tasksRepo.findTasksByProjectIdAndStatusAndDueDate(projectId, taskStatus, dueDate.orElse(null));
    }
}
