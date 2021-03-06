package com.tiy.web;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by dbashizi on 1/9/17.
 */
@RestController
public class GameTrackerJSONController {

    @Autowired
    GameRepository games;

    @Autowired
    FileAsStringRepository fileRepo;

    @Autowired
    PresenceUserRepository userRepo;

    @RequestMapping(path = "/games.json", method = RequestMethod.GET)
    public ArrayList<Game> getGames() {
        ArrayList<Game> gameList = new ArrayList<Game>();
        Iterable<Game> allGames = games.findAll();
        for (Game game : allGames) {
            game.setTestDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            games.save(game);
            gameList.add(game);
        }

        return gameList;
    }

    @RequestMapping(path = "/users.json", method = RequestMethod.GET)
    public List<PresenceUser> getUsers() {
        Iterable<PresenceUser> users = userRepo.findAll();
        List<PresenceUser> usersList = new ArrayList<PresenceUser>();
        int userIndex = 0;
        Iterator<PresenceUser> usersIterator = users.iterator();
        while (usersIterator.hasNext()) {
            System.out.println("getting user " + userIndex);
            usersList.add(usersIterator.next());
            userIndex++;
        }
        return usersList;

    }

    @RequestMapping(path = "/test-upload-string.json", method = RequestMethod.POST)
    public FileAsString testUploadString(@RequestBody FileAsString fileAsString) {
        FileAsString savedToDB = null;

        if (fileAsString != null) {
            System.out.println("Got file!!!");
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));
//            System.out.println("fileAsString.fileString = " + fileAsString.getFileString());

            Base64 decoder = new Base64();
            byte[] decodedBytes = decoder.decode(fileAsString.getFileString());
            fileAsString.setFileBytes(decodedBytes);

            try {
                if (fileAsString.getFileName() == null || fileAsString.getFileName().trim().equals("")) {
                    fileAsString.setFileName("test.jpg");
                }

                savedToDB = fileRepo.save(fileAsString);

                FileOutputStream fileOuputStream = new FileOutputStream(fileAsString.getFileName());
                fileOuputStream.write(fileAsString.getFileBytes());
                fileOuputStream.close();
            } catch (Exception exception) {
                System.out.println("************* Issue writing out file *****************");
                exception.printStackTrace();
            }

        } else {
            System.out.println("No file!!!!");
        }

//        ArrayList<Game> gameList = new ArrayList<Game>();
//        Iterable<Game> allGames = games.findAll();
//        for (Game game : allGames) {
//            game.setTestDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
//            games.save(game);
//            gameList.add(game);
//        }
//
//        return gameList;
        if (savedToDB != null) {
            System.out.println("Returning version saved from DB");
            return savedToDB;
        } else {
            System.out.println("Echoing version passed in");
            return fileAsString;
        }
    }
    @RequestMapping(path = "/test-upload.json", method = RequestMethod.POST)
    public ArrayList<Game> testUpload(@RequestParam(value = "image", required = false) MultipartFile imageFile) {

        if (imageFile != null) {
            System.out.println("originalFileName: " + imageFile.getOriginalFilename());
        } else {
            System.out.println("no image!!!");
        }

        ArrayList<Game> gameList = new ArrayList<Game>();
        Iterable<Game> allGames = games.findAll();
        for (Game game : allGames) {
            game.setTestDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
            games.save(game);
            gameList.add(game);
        }

        return gameList;
    }
}
