package com.tiy.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
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

	@Autowired
	ContactRequestRepository contactRequestRepo;

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
	public void testEventContactRequest() {
		PresenceUser user1 = new PresenceUser();
		user1.setFirstName("Test1");
		user1.setLastName("Tester1");
		PresenceUser user2 = new PresenceUser();
		user2.setFirstName("Test2");
		user2.setLastName("Tester2");

		userRepo.save(user1);
		assertNotNull(user1.getId());
		userRepo.save(user2);
		assertNotNull(user2.getId());

		ContactRequest contactRequest = new ContactRequest();
		contactRequest.setStatus("PENDING");
		contactRequest.setRequester(user1);
		contactRequest.setRequestee(user2);

		contactRequestRepo.save(contactRequest);
		assertNotNull(contactRequest.getId());
		assertNotNull(contactRequest.getRequestee());
		assertNotNull(contactRequest.getRequester());
		assertEquals(user2.getId(), contactRequest.getRequestee().getId());
		assertEquals(user1.getId(), contactRequest.getRequester().getId());

		// retrieve the user from the database, and make sure it has the contact request
		// associated with it
		user1 = userRepo.findOne(user1.getId());
		assertNotNull(user1.getRequestsMade());
		assertEquals(1, user1.getRequestsMade().size());
		user2 = userRepo.findOne(user2.getId());
		assertNotNull(user2.getRequestsReceived());
		assertEquals(1, user2.getRequestsReceived().size());

		System.out.println("User 1: " + user1.getId());
		System.out.println("User 2: " + user2.getId());
		System.out.println("Contact Requester " + contactRequest.getRequester().getId());
		System.out.println("Contact Requestee " + contactRequest.getRequestee().getId());
		System.out.println("Contact Request ID: " + contactRequest.getId());

//		contactRequestRepo.delete(contactRequest);
//		userRepo.delete(user1);
//		userRepo.delete(user2);
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
