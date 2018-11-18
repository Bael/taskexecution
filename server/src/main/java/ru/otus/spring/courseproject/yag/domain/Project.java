package ru.otus.spring.courseproject.yag.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 400)
    private String name;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
//    @Column(name = "project_status")
    private ProjectStatus status;

}
