package ru.otus.spring.courseproject.yag.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    private long id;
    @Column(length = 400)
    private String name;

    private long kanbanId;

    @ManyToOne
    private Project project;

}
