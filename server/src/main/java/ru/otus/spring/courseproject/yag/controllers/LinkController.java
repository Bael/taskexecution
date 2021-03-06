package ru.otus.spring.courseproject.yag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.courseproject.yag.data.LinkRepository;
import ru.otus.spring.courseproject.yag.data.ProjectRepository;
import ru.otus.spring.courseproject.yag.data.TaskRepository;
import ru.otus.spring.courseproject.yag.domain.Link;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.dto.LinkDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LinkController {
    @Autowired
    LinkRepository linkRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/api/links")
    public List<LinkDTO> getLinks() {
        return linkRepository.findAll().stream().map(LinkDTO::fromLink).collect(Collectors.toList());
    }

    @GetMapping("/api/projects/{id}/links")
    public List<LinkDTO> getByProject(@PathVariable long id) {

        Project p = projectRepository.findById(id).orElseThrow(RuntimeException::new);
        return linkRepository.findByProject(p).stream().map(LinkDTO::fromLink).collect(Collectors.toList());
    }




    @PostMapping("/api/links")
    @Transactional
    public LinkDTO createLink(@RequestBody LinkDTO dto) {
        Link link = new Link();
        link.setTarget(taskRepository.findById(dto.getTarget()).orElseThrow(RuntimeException::new));
        link.setSource(taskRepository.findById(dto.getSource()).orElseThrow(RuntimeException::new));
        link.setType(dto.getType());

        Project p = projectRepository.findById(dto.getProject()).orElseThrow(RuntimeException::new);

        link.setProject(p);
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
