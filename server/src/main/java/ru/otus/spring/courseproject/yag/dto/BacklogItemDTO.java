package ru.otus.spring.courseproject.yag.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.courseproject.yag.domain.BacklogItem;

import java.util.ArrayList;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BacklogItemDTO {
    private long id;
    private String name;
    private int priority;
    private ArrayList<String> options;

    public static BacklogItemDTO fromBacklogItem(BacklogItem item) {
        return BacklogItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .priority(item.getPriority())
                .options(item.getOptions()).build();
    }

}
