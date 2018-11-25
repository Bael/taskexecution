package ru.otus.spring.courseproject.yag.data;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.courseproject.yag.domain.Link;
import ru.otus.spring.courseproject.yag.domain.Project;

import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Long> {

    List<Link> findAll();

    List<Link> findByProject(Project project);
}
