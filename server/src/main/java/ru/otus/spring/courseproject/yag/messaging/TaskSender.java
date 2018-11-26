package ru.otus.spring.courseproject.yag.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.otus.spring.courseproject.yag.data.TaskRepository;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.domain.Task;
import ru.otus.spring.courseproject.yag.dto.TaskDTO;

import java.util.List;
import java.util.Objects;

@Component
public class TaskSender {

    private final TaskRepository taskRepository;


    @Autowired
    public TaskSender(TaskRepository taskRepository, JmsTemplate jmsTemplate) {
        this.taskRepository = taskRepository;
        this.jmsTemplate = jmsTemplate;
    }

    private final JmsTemplate jmsTemplate;

    public void sendTasks(String destination, Project project) {
        Objects.requireNonNull(project);

        List<Task> tasks = taskRepository.findByProject(project);
        tasks.forEach(task -> sendTask(destination, TaskDTO.fromTask(task)));
    }

    public void sendTask(String destination, TaskDTO task){
            this.jmsTemplate.convertAndSend(destination, task);
    }


	@JmsListener(destination="jms.task-reply-queue")
	public void process(String body, @Header("ID") long id){
        Task task = this.taskRepository.findById(id).orElseThrow(RuntimeException::new);
        task.setSynced(true);
	}


}
