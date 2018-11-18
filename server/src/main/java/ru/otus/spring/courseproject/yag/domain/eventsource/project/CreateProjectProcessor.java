package ru.otus.spring.courseproject.yag.domain.eventsource.project;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.courseproject.yag.data.ProjectRepository;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.domain.eventsource.EventProcessor;
import ru.otus.spring.courseproject.yag.dto.ProjectDTO;
import org.springframework.stereotype.Service;


@Service
public class CreateProjectProcessor implements EventProcessor<CreateProjectEvent> {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public CreateProjectEventResult process(CreateProjectEvent createProjectEvent) {
        Project project = new Project();
        project.setName(createProjectEvent.projectDTO.getName());
        project.setDescription(createProjectEvent.projectDTO.getDescription());
        projectRepository.save(project);

        CreateProjectEventResult result = new CreateProjectEventResult();
        result.dto = ProjectDTO.fromProject(project);

        return result;
    }

    @Override
    public boolean matches(CreateProjectEvent createProjectEvent) {
        return true;
    }
}
