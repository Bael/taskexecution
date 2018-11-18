package ru.otus.spring.courseproject.yag.data;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.courseproject.yag.domain.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
}
