package ru.otus.spring.courseproject.yag.services;

import ru.otus.spring.courseproject.yag.domain.Task;

import java.util.List;

public interface TaskService {
    void sendTasks(List<Task> tasks);

}
