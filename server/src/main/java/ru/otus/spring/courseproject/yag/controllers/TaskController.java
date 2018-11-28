package ru.otus.spring.courseproject.yag.controllers;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.courseproject.yag.data.ProjectRepository;
import ru.otus.spring.courseproject.yag.data.TaskRepository;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.domain.Task;
import ru.otus.spring.courseproject.yag.dto.LinkDTO;
import ru.otus.spring.courseproject.yag.dto.TaskDTO;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;


    @GetMapping("/api/tasks")
    public List<TaskDTO> getTasks() {
        return taskRepository.findAll().stream().map(TaskDTO::fromTask).collect(Collectors.toList());
    }

    @GetMapping("/api/projects/{id}/tasks")
    public List<TaskDTO> getByProject(@PathVariable long id) {
        Project p = projectRepository.findById(id).orElseThrow(RuntimeException::new);
        return taskRepository.findByProject(p).stream().map(TaskDTO::fromTask).collect(Collectors.toList());
    }

    @PostMapping("/api/tasks")
    @Transactional
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {

        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setDuration(taskDTO.getDuration());

        Project p = projectRepository.findById(taskDTO.getProject()).orElseThrow(RuntimeException::new);
        task.setProject(p);
//        task.setStartDate(LocalDate.now(ZoneId.of("UTC")));
        // 2018-01-31
        LocalDate startDate = LocalDate.parse(taskDTO.getStartDate().substring(0, 10));
        task.setStartDate(startDate);
        task.setProgress(taskDTO.getProgress());
        if (taskDTO.getParent() > 0)
        {
            Task parent = taskRepository.findById(taskDTO.getParent()).orElseThrow(() -> new ObjectNotFoundException(taskDTO.getParent(),
                    "parent Task"));
            task.setParent(parent);

        }
        taskRepository.save(task);
        return TaskDTO.fromTask(task);
    }

    @PutMapping("/api/tasks/{id}")
    @Transactional
    public TaskDTO updateTask(@PathVariable int id, @RequestBody TaskDTO taskDTO) {

        Task task = taskRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ObjectNotFoundException(id, "parent Task"));

        task.setDescription(taskDTO.getDescription());
        task.setDuration(taskDTO.getDuration());
        LocalDate startDate = LocalDate.parse(taskDTO.getStartDate().substring(0, 10));
        task.setStartDate(startDate);
        task.setProgress(taskDTO.getProgress());
        if (taskDTO.getParent() > 0)
        {
            Task parent = taskRepository.findById(taskDTO.getParent()).orElseThrow(() -> new ObjectNotFoundException(taskDTO.getParent(),
                    "parent Task"));
            task.setParent(parent);
        }
        else {
            task.setParent(null);
        }

        taskRepository.save(task);
        return TaskDTO.fromTask(task);
    }



    @DeleteMapping("/api/tasks/{id}")
    @Transactional
    public void deleteTask(@PathVariable long id) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "parent Task"));

        taskRepository.deleteById(id);

    }



}
