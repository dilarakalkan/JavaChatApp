package com.example.chatapp;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatappApplication {

	@Autowired
	private SocketIOServer server;

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
		System.out.println("hello");
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			server.start();
			Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
		};
	}
}
