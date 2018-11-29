package ru.otus.spring.courseproject.yag.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.courseproject.yag.domain.Board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private long id;
    private String name;
    private long kanbanId;
    private long projectId;
    private String projectName;

    public static BoardDTO fromBoard(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setKanbanId(board.getKanbanId());
        dto.setName(board.getName());
        if (board.getProject() != null) {
            dto.setProjectId(board.getProject().getId());
            dto.setProjectName(board.getProject().getName());
        }

        return dto;

    }

}
