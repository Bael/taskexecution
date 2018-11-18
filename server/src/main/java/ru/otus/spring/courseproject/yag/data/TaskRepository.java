package ru.otus.spring.courseproject.yag.data;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.courseproject.yag.domain.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAll();

}
