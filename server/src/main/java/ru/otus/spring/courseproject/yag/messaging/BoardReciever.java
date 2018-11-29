package ru.otus.spring.courseproject.yag.messaging;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.otus.spring.courseproject.yag.data.BoardRepository;
import ru.otus.spring.courseproject.yag.domain.Board;
import ru.otus.spring.courseproject.yag.dto.TaskDTO;
import ru.otus.spring.dto.SyncBoard;

import javax.transaction.Transactional;

@Component
public class BoardReciever {


    @Autowired
    BoardRepository boardRepository;

    @JmsListener(destination = "jms.board-queue")
    @Transactional
    public void processBoard(SyncBoard syncBoard) {
        System.out.println("processBoard called " + syncBoard);
        Board board = boardRepository.findByKanbanId(syncBoard.getKanbanBoardId()).orElse(new Board());
        board.setKanbanId(syncBoard.getKanbanBoardId());
        board.setName(syncBoard.getName());
        boardRepository.save(board);
    }
}
