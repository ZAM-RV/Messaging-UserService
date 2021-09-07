package com.example.userservice;

import com.example.userservice.Services.Registration.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class MessagingUserServiceApplication {

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean lfb) {
        return new ValidatingMongoEventListener(lfb);
    }

    public static void main(String[] args) {
        SpringApplication.run(MessagingUserServiceApplication.class, args);
    }

    class Runner implements CommandLineRunner{

        @Override
        public void run(String... args) throws Exception {
            System.out.println(new StandardPasswordEncoder().encode("password123"));
        }
    }

}
