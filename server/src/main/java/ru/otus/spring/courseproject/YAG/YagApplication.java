package ru.otus.spring.courseproject.YAG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.courseproject.YAG.data.LinkRepository;
import ru.otus.spring.courseproject.YAG.data.TaskRepository;
import ru.otus.spring.courseproject.YAG.domain.Link;
import ru.otus.spring.courseproject.YAG.domain.Task;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
public class YagApplication {

	public static void main(String[] args) {
		SpringApplication.run(YagApplication.class, args);
	}

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	LinkRepository linkRepository;

	@PostConstruct
	public void init () {

		linkRepository.deleteAll();
		taskRepository.deleteAll();

		Task first = new Task();
		first.setDescription("Task one");
		first.setStartDate(LocalDate.now(ZoneId.of("UTC")));
		first.setDuration(3);
		first.setProgress(.6);
		taskRepository.save(first);

		Task second = new Task();
		second.setDescription("Task one");
		second.setStartDate(LocalDate.now(ZoneId.of("UTC")));
		second.setDuration(2);
		second.setProgress(.4);
		second.setParent(first);

		taskRepository.save(second);

		Link link = new Link();
		link.setSource(first);
		link.setTarget(second);
		link.setType("0");
		linkRepository.save(link);

		//   {id: 1, text: 'Task #1', start_date: '2017-04-15 00:00', duration: 3, progress: 0.6},
		//   {id: 2, text: 'Task #2', start_date: '2017-04-18 00:00', duration: 3, progress: 0.4}

	}
}
