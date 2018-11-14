package ru.otus.spring.courseproject.YAG.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.courseproject.YAG.data.LinkRepository;
import ru.otus.spring.courseproject.YAG.data.TaskRepository;
import ru.otus.spring.courseproject.YAG.domain.Link;
import ru.otus.spring.courseproject.YAG.dto.LinkDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LinkController {
    @Autowired
    LinkRepository linkRepository;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/api/links")
    public List<LinkDTO> getLinks() {
        return linkRepository.findAll().stream().map(LinkDTO::fromLink).collect(Collectors.toList());
    }

    @PostMapping("/api/links")
    @Transactional
    public LinkDTO createLink(@RequestBody LinkDTO dto) {
        Link link = new Link();
        link.setTarget(taskRepository.findById(dto.getTarget()).orElseThrow(RuntimeException::new));
        link.setSource(taskRepository.findById(dto.getSource()).orElseThrow(RuntimeException::new));
        link.setType(dto.getType());
        linkRepository.save(link);
        return LinkDTO.fromLink(link);
    }

    @PutMapping("/api/links/{id}")
    @Transactional
    public LinkDTO updateLink(@PathVariable int id, @RequestBody LinkDTO dto) {
        Link link = linkRepository.findById((long) id).orElseThrow(RuntimeException::new);

        link.setTarget(taskRepository.findById(dto.getTarget()).orElseThrow(RuntimeException::new));
        link.setSource(taskRepository.findById(dto.getSource()).orElseThrow(RuntimeException::new));
        link.setType(dto.getType());
        linkRepository.save(link);
        return LinkDTO.fromLink(link);
    }


    @DeleteMapping("/api/links/{id}")
    @Transactional
    public void deleteTask(@PathVariable int id) {
        linkRepository.deleteById((long) id);
    }

}
