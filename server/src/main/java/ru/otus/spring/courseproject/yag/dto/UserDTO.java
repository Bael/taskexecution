package ru.otus.spring.courseproject.yag.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.courseproject.yag.domain.User;

@Data
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String fio;
    private String login;
    private String password;
    private String avatarUrl;

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFio(user.getFio());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setAvatarUrl(user.getAvatarUrl());
        return userDTO;
    }
}
