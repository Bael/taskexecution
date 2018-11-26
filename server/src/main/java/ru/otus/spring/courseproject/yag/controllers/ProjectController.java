package ru.otus.spring.courseproject.yag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.courseproject.yag.data.ProjectRepository;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.domain.ProjectStatus;
import ru.otus.spring.courseproject.yag.dto.ProjectDTO;
import ru.otus.spring.courseproject.yag.messaging.TaskSender;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("api/projects")
    public List<ProjectDTO> getProjects() {
        return projectRepository.findAll().stream().map(ProjectDTO::fromProject).collect(Collectors.toList());
    }

    @PostMapping("api/projects")
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {

        Project project = new Project();
        project.setStatus(ProjectStatus.CREATED);
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        projectRepository.save(project);
        return ProjectDTO.fromProject(project);

    }

    @PutMapping("api/projects/{id}")
    public ProjectDTO updateProject(@PathVariable long id, @RequestBody ProjectDTO projectDTO) {

        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));


        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        projectRepository.save(project);
        return ProjectDTO.fromProject(project);

    }

    @GetMapping("api/projects/{id}")
    public ProjectDTO getProject(@PathVariable long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        return ProjectDTO.fromProject(project);
    }

    @PutMapping("api/projects/archive/{id}")
    public void archiveProject(@PathVariable long id, @RequestBody ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        project.setStatus(ProjectStatus.ARCHIVED);
        projectRepository.save(project);

    }

    @Autowired
    TaskSender taskSender;

    @PostMapping("api/projects/send/{id}")
    public void sendProjectTasks(@PathVariable long id, @RequestBody ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        taskSender.sendTasks("jms-tasks-queue", project);
    }

    @DeleteMapping("api/projects/{id}")
    public void deleteProject(@PathVariable long id, @RequestBody ProjectDTO projectDTO) {
        projectRepository.deleteById(id);
    }

}
