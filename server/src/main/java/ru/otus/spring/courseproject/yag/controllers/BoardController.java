package ru.otus.spring.courseproject.yag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.courseproject.yag.data.BoardRepository;
import ru.otus.spring.courseproject.yag.data.ProjectRepository;
import ru.otus.spring.courseproject.yag.domain.Board;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.dto.BoardDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BoardController {


    private final ProjectRepository projectRepository;
    private final BoardRepository boardRepository;


    public BoardController(BoardRepository boardRepository, ProjectRepository projectRepository) {
        this.boardRepository = boardRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("api/boards")
    public List<BoardDTO> getBoards() {
        return boardRepository.findAll().stream().map(BoardDTO::fromBoard).collect(Collectors.toList());
    }


    @PutMapping("api/boards/{id}")
    @Transactional
    public BoardDTO setProject(@PathVariable long id, @RequestBody BoardDTO boardDTO) {
        Board board = boardRepository.findById(id).orElseThrow(RuntimeException::new);
        Project p = projectRepository.findById(boardDTO.getProjectId()).orElseThrow(RuntimeException::new);
        board.setProject(p);

        board = boardRepository.save(board);
        return BoardDTO.fromBoard(board);
    }
}
