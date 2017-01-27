package com.tiy.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTrackerSpringApplicationTests {

	@Autowired
	GameRepository games;

	@Autowired
	FileAsStringRepository fileRepo;

	@Autowired
	PresenceUserRepository userRepo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUserRepo() {
		PresenceUser user = new PresenceUser();
		user.setFirstName("Tester");
		user.setLastName("Tester");

		user = userRepo.save(user);

		assertNotNull(user.getId());
		System.out.println("Created user with ID " + user.getId());

		userRepo.delete(user);
	}

	@Test
	public void testRepo() {
		Iterable<Game> allGames = games.findAll();
		for (Game game : allGames) {
			System.out.println("Game: " + game.getName());
		}
	}

	@Test
	public void testFileCreation() {
		FileAsString file = new FileAsString();
		file.setFileName("unit-test-file.jpg");
		try {
			String content = new Scanner(new File("test.txt")).useDelimiter("\\Z").next();
			System.out.println("Content length: " + content.length());
			file.setFileString(content);
		} catch (Exception exception) {
			System.out.println("Unable to read file with exception: " + exception.getMessage());
		}

		file = fileRepo.save(file);

		assertNotNull(file.getId());

		fileRepo.delete(file);
	}

	@Test
	public void testFileRepo() {
		Iterable<FileAsString> allFiles = fileRepo.findAll();
		for (FileAsString file : allFiles) {
			System.out.println("File: " + file);
		}
	}
}
