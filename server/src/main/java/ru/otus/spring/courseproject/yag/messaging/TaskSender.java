package ru.otus.spring.courseproject.yag.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.otus.spring.courseproject.yag.data.BoardRepository;
import ru.otus.spring.courseproject.yag.data.TaskRepository;
import ru.otus.spring.courseproject.yag.domain.Board;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.domain.Task;
import ru.otus.spring.courseproject.yag.dto.TaskDTO;
import ru.otus.spring.dto.SyncTask;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TaskSender {

    private final TaskRepository taskRepository;


    @Autowired
    public TaskSender(TaskRepository taskRepository, JmsTemplate jmsTemplate) {
        this.taskRepository = taskRepository;
        this.jmsTemplate = jmsTemplate;
    }

    private final JmsTemplate jmsTemplate;

    @Autowired
    BoardRepository boardRepository;

    public void sendTasks(String destination, Project project) {
        Objects.requireNonNull(project);

        long boardId = 0;
        Optional<Board> board = boardRepository.findByProject(project);

        List<Task> tasks = taskRepository.findByProject(project);
        tasks.forEach(task -> {
            SyncTask syncTask = new SyncTask();
            if (board.isPresent()) {

                System.out.println("sync board id " + board.get().getId());

                syncTask.setBoardId(board.get().getKanbanId());
            }
            syncTask.setProjectTaskId(task.getId());
            syncTask.setDescription(task.getDescription());

            sendTask(destination, syncTask);
        });
    }

    public void sendTask(String destination, SyncTask task){
            this.jmsTemplate.convertAndSend(destination, task);
    }


	@JmsListener(destination="jms.task-reply-queue")
	public void process(String body, @Header("ID") long id){
        Task task = this.taskRepository.findById(id).orElseThrow(RuntimeException::new);
        task.setSynced(true);
	}


}
