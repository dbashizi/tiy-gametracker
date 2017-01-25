package com.tiy.web;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dbashizi on 1/9/17.
 */
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByGenre(String genre);
    List<Game> findByUserAndGenre(User user, String genre);
    List<Game> findByReleaseYear(int year);
    List<Game> findByUserAndReleaseYear(User user, int year);
    List<Game> findByUser(User user);

    @Query("SELECT g FROM Game g WHERE g.name LIKE ?1%")
    List<Game> findByNameStartsWith(String name);
}
