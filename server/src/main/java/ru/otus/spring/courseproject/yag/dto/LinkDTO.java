package ru.otus.spring.courseproject.yag.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.courseproject.yag.domain.Link;

import java.util.Objects;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LinkDTO {

    private long id;
    private Long source;
    private Long target;
    private String type;
    private Long project;

    public static LinkDTO fromLink(Link link) {

        Objects.requireNonNull(link.getSource(), "source is null");
        Objects.requireNonNull(link.getTarget(), "target is null");

        return LinkDTO.builder().id(link.getId())
                .source(link.getSource().getId())
                .target(link.getTarget().getId())
                .project(link.getProject().getId())
                .type(link.getType()).build();

    }
}
