package ru.otus.spring.courseproject.YAG.data;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.courseproject.YAG.domain.Link;

import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Long> {

    List<Link> findAll();
}
