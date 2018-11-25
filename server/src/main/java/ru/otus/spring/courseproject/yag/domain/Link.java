package ru.otus.spring.courseproject.yag.domain;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Link {

    @Id
    @GeneratedValue
    private long id;


    @ManyToOne
    private Project project;

    @ManyToOne
    private Task source;

    @ManyToOne
    private Task target;

    private String type;

}
