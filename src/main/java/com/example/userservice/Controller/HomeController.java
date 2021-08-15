package com.example.userservice.Controller;

import com.example.userservice.Dto.User;
import com.example.userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    private UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String home(){
        /*
        User testUser = User.builder()
                .firstName("Kabe")
                .lastName("grup")
                .email("Sammy@hotmail.com")
                .password("plainPassword")
                .build();

         */
        System.out.println("I have hit the home controller");
        User testUser = new User();
        testUser.setFirstName("kage");
        testUser.setLastName("Goru");
        testUser.setEmail("kage@hotmail.com");

        userRepository.save(testUser);
        System.out.println("I have saved");
        return "<h1>Hello World</h1>";
    }
}
