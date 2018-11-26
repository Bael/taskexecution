package ru.otus.spring.courseproject.yag.domain;

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
//    @Override
//    public String toString() {
//        return "Task{" +
//                "id=" + id +
//                ", startDate=" + startDate +
//                ", description='" + description + '\'' +
//                ", progress=" + progress +
//                ", duration=" + duration +
//                ", project=" + project +
//                '}';
//    }

    @Id
    @GeneratedValue
    private long id;
    private LocalDate startDate;
    private String description;
    private double progress;
    private int duration;
    private boolean isSynced;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Task parent;

    public String toString() {
        return "Task(id=" + this.getId() + ", startDate=" + this.getStartDate() + ", description=" + this.getDescription() + ", progress=" + this.getProgress() + ", duration=" + this.getDuration() + ", project=" + this.getProject() + ", parent=" + this.getParent() + ")";
    }
}
