package io.lvlvforever.babysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@SpringBootApplication
@EnableWebSecurity
public class BabysiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabysiteApplication.class, args);
    }

}

