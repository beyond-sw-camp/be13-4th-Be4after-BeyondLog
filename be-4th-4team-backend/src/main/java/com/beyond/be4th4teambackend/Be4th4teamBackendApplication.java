package com.beyond.be4th4teambackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Be4th4teamBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Be4th4teamBackendApplication.class, args);
    }

}
