package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.mongodb.main.MongoDBMain;


@SpringBootApplication
public class SpringTestApplication {

	public static void main(String[] args) {
		MongoDBMain.run(); //몽고DB 시작
		SpringApplication.run(SpringTestApplication.class, args); //스프링 부트 시작
	}
}
