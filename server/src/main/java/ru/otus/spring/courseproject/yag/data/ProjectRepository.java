package ru.otus.spring.courseproject.yag.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.domain.ProjectStatus;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Query("select p from Project p where p.status <> 'ARCHIVED'")
    List<Project> findAll();
}
