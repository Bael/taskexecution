package ru.otus.spring.courseproject.yag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.courseproject.yag.data.BacklogItemRepository;
import ru.otus.spring.courseproject.yag.data.ProjectRepository;
import ru.otus.spring.courseproject.yag.data.TaskRepository;
import ru.otus.spring.courseproject.yag.domain.BacklogItem;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.domain.ProjectStatus;
import ru.otus.spring.courseproject.yag.domain.Task;
import ru.otus.spring.courseproject.yag.dto.BacklogItemDTO;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BacklogItemController {

    private final BacklogItemRepository backlogItemRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public BacklogItemController(BacklogItemRepository backlogItemRepository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.backlogItemRepository = backlogItemRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/api/backlog")
    public List<BacklogItemDTO> getItems() {
        List<BacklogItemDTO> list = new ArrayList<>();
        backlogItemRepository.findAll(new Sort(Sort.Direction.DESC, "priority"))
                .forEach(backlogItem -> list.add(BacklogItemDTO.fromBacklogItem(backlogItem)));
        return list;
    }

    @GetMapping("/api/backlog/{id}")
    public BacklogItemDTO getItem(@PathVariable long id) {
        return BacklogItemDTO.fromBacklogItem(backlogItemRepository.findById(id)
                .orElseThrow(RuntimeException::new));
    }

    @PutMapping("/api/backlog/{id}")
    @Transactional
    public BacklogItemDTO updateItem(@PathVariable long id, @RequestBody BacklogItemDTO itemDTO) {
        BacklogItem item = backlogItemRepository.findById(id).orElseThrow(RuntimeException::new);

        item.setName(itemDTO.getName());
        item.setPriority(itemDTO.getPriority());
        item.setOptions(itemDTO.getOptions());
        backlogItemRepository.save(item);

        return BacklogItemDTO.fromBacklogItem(item);
    }

    @PostMapping("/api/backlog")
    @Transactional
    public BacklogItemDTO createItem(@RequestBody BacklogItemDTO itemDTO) {

        BacklogItem item = new BacklogItem();
        item.setName(itemDTO.getName());
        item.setPriority(itemDTO.getPriority());
        item.setOptions(itemDTO.getOptions());
        backlogItemRepository.save(item);
        return BacklogItemDTO.fromBacklogItem(item);
    }

    @PostMapping("/api/backlog/convert/{id}")
    @Transactional
    public void convertItem(@PathVariable long id, @RequestBody BacklogItemDTO itemDTO) {
        BacklogItem item = backlogItemRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id));
        Project p = new Project();
        p.setName(item.getName());
        p.setStatus(ProjectStatus.CREATED);
        projectRepository.save(p);
        LocalDate startDate = LocalDate.now(ZoneId.of("UTC"));
        int count = 1;

        for (String option : itemDTO.getOptions()) {

            Task t = new Task();
            t.setDescription(option);
            t.setProject(p);
            t.setDuration(1);
            // we begin it tomorrow ...
            t.setStartDate(startDate.plusDays(count++));
            taskRepository.save(t);

        }

        backlogItemRepository.delete(item);
    }

    @DeleteMapping("/api/backlog/{id}")
    public void deleteItem(@PathVariable long id, @RequestBody BacklogItemDTO itemDTO) {
        backlogItemRepository.deleteById(id);
    }


}
