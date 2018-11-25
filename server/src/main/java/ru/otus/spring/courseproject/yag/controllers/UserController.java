package ru.otus.spring.courseproject.yag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.courseproject.yag.data.UserRepository;
import ru.otus.spring.courseproject.yag.domain.User;
import ru.otus.spring.courseproject.yag.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }


    @PostMapping("/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setFio(userDTO.getFio());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setPassword(userDTO.getPassword());

        User savedUser = userRepository.save(user);
        return UserDTO.fromUser(savedUser);

    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);


        user.setFio(userDTO.getFio());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setPassword(userDTO.getPassword());

        User savedUser = userRepository.save(user);
        return UserDTO.fromUser(savedUser);

    }
}
