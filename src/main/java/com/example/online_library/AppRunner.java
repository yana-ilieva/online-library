package com.example.online_library;

import com.example.online_library.model.User;
import com.example.online_library.repository.UserRepository;
import com.example.online_library.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    public AppRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .firstName("Yana")
                .lastName("Ilieva")
                .password("12345")
                .phoneNumber("0888202284")
                .registrationDate(new Date())
                .build();
        userRepository.save(user);
    }
}
