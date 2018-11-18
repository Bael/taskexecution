package ru.otus.spring.courseproject.yag.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.domain.ProjectStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private String status;

    public static ProjectDTO fromProject(Project project) {
        return ProjectDTO.builder().id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .status(project.getStatus().toString())
                .build();


    }


}
