package com.kcbgroup.kihoro_tech_app_v1.services;

import com.kcbgroup.kihoro_tech_app_v1.models.TaskStatusEnum;
import com.kcbgroup.kihoro_tech_app_v1.models.entity.Project;
import com.kcbgroup.kihoro_tech_app_v1.models.repositories.ProjectRepo;
import com.kcbgroup.kihoro_tech_app_v1.models.repositories.TasksRepo;
import com.kcbgroup.kihoro_tech_app_v1.responses.ProjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private TasksRepo tasksRepo;


    public Project saveProject(Project project)
    {
        return projectRepo.save(project);
    }

    public List<Project> getAllProject()
    {
        return projectRepo.findAll();
    }

    public Optional<Project> getProjectById(Long projectId)
    {
        return projectRepo.findById(projectId);
    }

    public List<ProjectSummary> getProjectSummary() {
        List<Project> projects = projectRepo.findAll();
        List<ProjectSummary> projectSummaries = new ArrayList<>();

        for (Project project : projects) {
            Map<TaskStatusEnum, Long> taskCountByStatus = tasksRepo.countTasksByProjectIdGroupedByStatus(project.getId());
            ProjectSummary projectSummary = new ProjectSummary(
                    project.getId(),
                    project.getName(),
                    taskCountByStatus
            );
            projectSummaries.add(projectSummary);
        }

        return projectSummaries;
    }
}
