package ru.otus.spring.courseproject.yag.data;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.courseproject.yag.domain.Board;
import ru.otus.spring.courseproject.yag.domain.Project;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<Board, Long> {
    Optional<Board> findByKanbanId(long kanbanId);

    Optional<Board> findByProject(Project project);

    List<Board> findAll();
}
