package ru.otus.spring.courseproject.yag.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class User {
    private long id;
    private String fio;
    private String login;
    private String avatarUrl;

}
