package com.ray.musicbox;

import com.ray.musicbox.repository.MusicRepository;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicboxApplication implements CommandLineRunner {

	@Autowired
	private MusicRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MusicboxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal main = new Principal(repository);
		main.menu();
	}
}
