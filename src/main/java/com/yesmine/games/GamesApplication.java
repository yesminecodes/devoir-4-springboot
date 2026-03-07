package com.yesmine.games;

import com.yesmine.games.model.Game;
import com.yesmine.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class GamesApplication implements CommandLineRunner {

    @Autowired
    GameService gameService;

    public static void main(String[] args) {
        SpringApplication.run(GamesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*gameService.saveGame(new Game("FIFA 25", 60.0, new Date()));
        gameService.saveGame(new Game("Call of Duty", 55.0, new Date()));
        gameService.saveGame(new Game("Minecraft", 30.0, new Date()));
        gameService.saveGame(new Game("GTA V", 40.0, new Date()));
        gameService.saveGame(new Game("Fortnite", 0.0, new Date()));*/
    }

}
