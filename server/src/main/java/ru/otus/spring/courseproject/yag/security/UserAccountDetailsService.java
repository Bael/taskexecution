package ru.otus.spring.courseproject.yag.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.spring.courseproject.yag.data.UserRepository;
import ru.otus.spring.courseproject.yag.domain.User;
import ru.otus.spring.courseproject.yag.dto.UserDTO;

import java.util.List;

@Service
public class UserAccountDetailsService implements UserDetailsService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Autowired
    public UserAccountDetailsService(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("username for log in " + username);
        User user = userRepository.findUserByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }

    public User createUser(UserDTO userDTO) {
        if (userRepository.findUserByLogin(userDTO.getLogin()) != null) {
            throw new RuntimeException(userDTO.getLogin());
        }

        User newUserAccount = User.builder().login(userDTO.getLogin())
                .password(encoder.encode(userDTO.getPassword()))
                .role(userDTO.getRole())
                .build();
        return userRepository.save(newUserAccount);

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}



