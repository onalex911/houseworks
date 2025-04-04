package ru.onalex.hw2903;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class Hw2903Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw2903Application.class, args);
    }
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return  new HiddenHttpMethodFilter();
    }

    @Bean
    public Faker faker(){
        return new Faker();
    }

}
