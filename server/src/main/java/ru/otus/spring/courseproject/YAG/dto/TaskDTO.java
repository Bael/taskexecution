package ru.otus.spring.courseproject.YAG.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.courseproject.YAG.domain.Task;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TaskDTO {

    private long id;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("text")
    private String description;
    private double progress;
    private int duration;
    private Long parent;
    private String executor;

    public static TaskDTO fromTask(Task task) {
        TaskDTOBuilder b = TaskDTO.builder().id(task.getId())
                .duration(task.getDuration())
                .progress(task.getProgress())
                .description(task.getDescription());
        if (task.getParent() != null) {
            b.parent(task.getParent().getId());
        }
        if (task.getStartDate() != null) {
            b.startDate(task.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00")));
        }

        return b.build();

    }

}
