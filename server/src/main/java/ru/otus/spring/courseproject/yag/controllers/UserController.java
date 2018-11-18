package ru.otus.spring.courseproject.yag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.courseproject.yag.data.UserRepository;
import ru.otus.spring.courseproject.yag.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }
}
