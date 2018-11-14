package ru.otus.spring.courseproject.YAG.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private long id;
    private LocalDate startDate;
    private String description;
    private double progress;
    private int duration;

    @ManyToOne
    private Task parent;

}
