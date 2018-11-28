package ru.otus.spring.courseproject.yag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.otus.spring.courseproject.yag.data.LinkRepository;
import ru.otus.spring.courseproject.yag.data.ProjectRepository;
import ru.otus.spring.courseproject.yag.data.TaskRepository;
import ru.otus.spring.courseproject.yag.data.UserRepository;
import ru.otus.spring.courseproject.yag.domain.Link;
import ru.otus.spring.courseproject.yag.domain.Project;
import ru.otus.spring.courseproject.yag.domain.Task;
import ru.otus.spring.courseproject.yag.domain.User;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootApplication
@EnableJpaRepositories
public class YagApplication {

	public static void main(String[] args) {
		SpringApplication.run(YagApplication.class, args);
	}

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	LinkRepository linkRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	UserRepository userRepository;


	@PostConstruct
	public void init () {


		linkRepository.deleteAll();
		taskRepository.deleteAll();
		projectRepository.deleteAll();
		userRepository.deleteAll();

		User user = new User();
		user.setFio("Васнецов И.В.");
		user.setLogin("vasya");
		user.setPassword("123");
		user.setAvatarUrl("");
		userRepository.save(user);

		User user2 = new User();
		user2.setFio("Петров А.Н.");
		user2.setLogin("peter");
		user2.setAvatarUrl("");
		userRepository.save(user2);



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

		Project project = new Project();
		project.setName("my homework");
		project.setDescription("do something already");
		projectRepository.save(project);



		//   {id: 1, text: 'Task #1', start_date: '2017-04-15 00:00', duration: 3, progress: 0.6},
		//   {id: 2, text: 'Task #2', start_date: '2017-04-18 00:00', duration: 3, progress: 0.4}

	}
}
