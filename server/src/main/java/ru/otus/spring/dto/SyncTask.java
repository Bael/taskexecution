package ru.otus.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
/**
 * Sync DTO for Task
 */
public class SyncTask {

    private UUID externalId;
    private long projectTaskId;
    private long kanbanTaskId;

    private long boardId;
    private Long projectId;

    private LocalDate startDate;
    private String description;
    private double progress;
    private String executor;
}
