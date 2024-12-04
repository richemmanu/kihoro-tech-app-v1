package com.kcbgroup.kihoro_tech_app_v1.controllers;

import com.kcbgroup.kihoro_tech_app_v1.models.entity.Project;
import com.kcbgroup.kihoro_tech_app_v1.models.entity.Tasks;
import com.kcbgroup.kihoro_tech_app_v1.models.requests.ProjectReq;
import com.kcbgroup.kihoro_tech_app_v1.models.requests.TasksReq;
import com.kcbgroup.kihoro_tech_app_v1.responses.ProjectSummary;
import com.kcbgroup.kihoro_tech_app_v1.services.ProjectService;
import com.kcbgroup.kihoro_tech_app_v1.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TasksService tasksService;

    @PostMapping("/projects")
    public Project createProject(@Valid @RequestBody ProjectReq projectReq)
    {
        Project project = new Project(null, projectReq.getName(), projectReq.getDescription(), null);
        return projectService.saveProject(project);
    }

    @GetMapping("/projects")
    public List<Project> getAllProject()
    {
        return projectService.getAllProject();
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId)
    {
        return projectService.getProjectById(projectId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //Tasks methods
    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<?> createProjectTask(@Valid @RequestBody TasksReq tasksReq, @PathVariable Long projectId)
    {
        Project project = projectService.getProjectById(projectId).orElse(null);
        Tasks tasks = new Tasks();
        if(project != null)
        {
            tasks.setProject(project);
            tasks.setTitle(tasksReq.getTitle().toUpperCase());
            tasks.setDescription(tasksReq.getDescription());
            tasks.setStatus(tasksReq.getStatus());
            tasks.setDueDate(tasksReq.getDueDate());
            tasksService.saveProjectTask(tasks);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/projects/{projectId}/tasks")
    public List<Tasks> getTasksByProjectAndFilters(@PathVariable Long projectId, @RequestParam Optional<String> taskStatus,
                                                   @RequestParam Optional<LocalDate> dueDate)
    {
        return tasksService.getTasksByProjectIdAndFilters(projectId, taskStatus, dueDate);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<?> editTask(@Valid @RequestBody TasksReq tasksReq, @PathVariable Long taskId)
    {
        Tasks task = tasksService.getTasksById(taskId).orElse(null);
        if(task != null)
        {
            task.setTitle(tasksReq.getTitle().toUpperCase());
            task.setDescription(tasksReq.getDescription());
            task.setStatus(tasksReq.getStatus());
            task.setDueDate(tasksReq.getDueDate());
            tasksService.saveProjectTask(task);
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId)
    {
        Tasks task = tasksService.getTasksById(taskId).orElse(null);
        if(task != null)
        {
            tasksService.deleteTasksById(task);
            return ResponseEntity.ok("Task deleted");
        }
        return ResponseEntity.notFound().build();
    }

    //aggregation and summation
    @GetMapping("/projects/summary")
    public List<ProjectSummary> getProjectSummary() {
        return projectService.getProjectSummary();
    }

}
