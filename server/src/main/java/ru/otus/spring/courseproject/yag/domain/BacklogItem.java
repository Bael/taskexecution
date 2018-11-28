package ru.otus.spring.courseproject.yag.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BacklogItem {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 400)
    private String name;

    @Column(name = "priority")
    private int priority;

    @Basic
    private ArrayList<String> options;

    public ArrayList<String> getOptions() {
        if (options == null) {
            options = new ArrayList<>();
        }

        return options;
    }
}
