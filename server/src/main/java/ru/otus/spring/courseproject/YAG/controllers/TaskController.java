package ru.otus.spring.courseproject.YAG.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.courseproject.YAG.data.TaskRepository;
import ru.otus.spring.courseproject.YAG.domain.Task;
import ru.otus.spring.courseproject.YAG.dto.LinkDTO;
import ru.otus.spring.courseproject.YAG.dto.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/api/tasks")
    public List<TaskDTO> getTasks() {
        return taskRepository.findAll().stream().map(TaskDTO::fromTask).collect(Collectors.toList());
    }
}
