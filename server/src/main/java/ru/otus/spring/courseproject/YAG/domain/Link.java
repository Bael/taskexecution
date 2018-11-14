package ru.otus.spring.courseproject.YAG.domain;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Link {

    @Id
    private long id;

    @ManyToOne
    private Task source;

    @ManyToOne
    private Task target;

    private String type;

}
