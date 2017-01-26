package com.tiy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by dbashizi on 1/9/17.
 */
@RestController
public class GameTrackerJSONController {

    @Autowired
    GameRepository games;

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

    @RequestMapping(path = "/test-upload.json", method = RequestMethod.POST)
    public ArrayList<Game> testUpload() {

//        System.out.println("originalFileName: " + imageFile.getOriginalFilename());

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
